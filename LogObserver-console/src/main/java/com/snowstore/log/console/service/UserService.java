package com.snowstore.log.console.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	public final static String ANONYMOUS_USER = "anonymousUser";

	public String getUsername() {
		Authentication currentuser = getAuthentication();
		if (checkUser(currentuser) && currentuser.getPrincipal() instanceof LdapUserDetailsImpl) {
			return ((LdapUserDetailsImpl) currentuser.getPrincipal()).getUsername();
		}
		return "";
	}

	/**
	 * @author SM
	 * @description 检查客户是否存在且非匿名
	 */
	private boolean checkUser(Authentication currentuser) {
		return null != currentuser && !currentuser.getPrincipal().equals(ANONYMOUS_USER);
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();

	}

}
