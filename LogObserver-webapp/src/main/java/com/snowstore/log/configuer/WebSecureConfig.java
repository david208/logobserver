package com.snowstore.log.configuer;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecureConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandlerImpl() {
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/403");
		return accessDeniedHandler;
	}

	private static final Logger logger = LoggerFactory.getLogger(WebSecureConfig.class);

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic().disable();
		http.authorizeRequests().antMatchers("/js/**", "/css/**", "/style/**", "/fonts/**","/image/**","/403").permitAll().anyRequest().hasAuthority("ROLE_LOGVIEWERGROUP").and().formLogin().loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login?error").permitAll();
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandlerImpl());
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		private Md5PasswordEncoder md5PasswordEncoder;

		@Value("${ldap.url}")
		private String ldapUrl;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthentication = auth.ldapAuthentication();
			ldapAuthentication.passwordCompare().passwordAttribute("userPassword").passwordEncoder(new PasswordEncoder() {

				@Override
				public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
					return true;
				}

				@Override
				public String encodePassword(String rawPass, Object salt) {
					MessageDigest digest;
					try {
						digest = MessageDigest.getInstance("MD5");
						digest.update(rawPass.getBytes("UTF8"));
						String md5Password = new String(Base64.encode(digest.digest()));
						return "{MD5}" + md5Password;
					} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
						logger.error("LDAP错误", e);
					}
					return "";
				}
			});

			ldapAuthentication.userSearchFilter("(cn={0})").userSearchBase("ou=IT,dc=JL,dc=com").groupSearchBase("ou=IT,dc=JL,dc=com").groupSearchFilter("(memberUid={0})").groupRoleAttribute("cn").contextSource().url(ldapUrl).managerDn("cn=Manager,dc=JL,dc=com")
					.managerPassword("123456");
		}
	}
}
