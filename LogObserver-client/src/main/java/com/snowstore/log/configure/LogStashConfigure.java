package com.snowstore.log.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.security.core.userdetails.UserDetails;

import com.snowstore.log.aop.UserLogAspect;
import com.snowstore.log.service.UserDetailDelegate;
import com.snowstore.log.service.UserLogObservable;
import com.snowstore.log.service.impl.UserDetailDelegateImpl;
import com.snowstore.log.service.impl.UserLogObservableRedisImpl;
import com.snowstore.log.vo.UserLogEsVo;

@Configuration
public class LogStashConfigure {

	
	@Value("${log.broker.url:172.16.200.134}")
	private String logBrokerUrl ;

	@Value("${snowstore.esb.system.self.code:}")
	private String systemCode;

	@Autowired(required=false)
	private UserDetailDelegate<UserDetails> userDetailDelegate;

	public RedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jredisConnectionFactory = new JedisConnectionFactory();
		jredisConnectionFactory.setHostName(logBrokerUrl);
		jredisConnectionFactory.setUsePool(true);
		jredisConnectionFactory.afterPropertiesSet();
		return jredisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, UserLogEsVo> redisTemplate() {
		RedisTemplate<String, UserLogEsVo> redisTemplate = new RedisTemplate<String, UserLogEsVo>();
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public UserLogObservable userLogObservableRedisImpl() {
		UserLogObservableRedisImpl userLogObservableRedisImpl = new UserLogObservableRedisImpl();
		userLogObservableRedisImpl.setSystemCode(systemCode);
		return userLogObservableRedisImpl;
	}

	@Bean
	public UserLogAspect userLogAspect() {
		UserLogAspect userLogAspect = new UserLogAspect();
		UserDetailDelegate<UserDetails> delegate = (null == userDetailDelegate) ? userDetailDelegateImpl() : userDetailDelegate;
		userLogAspect.setUserDetailDelegate(delegate);
		userLogAspect.setUserLogObservable(userLogObservableRedisImpl());
		return userLogAspect;

	}

	public String getLogBrokerUrl() {
		return logBrokerUrl;
	}

	public void setLogBrokerUrl(String logBrokerUrl) {
		this.logBrokerUrl = logBrokerUrl;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	//@Bean
	public UserDetailDelegate<UserDetails> userDetailDelegateImpl() {
		return new UserDetailDelegateImpl<UserDetails>();
	}

	public UserDetailDelegate<UserDetails> getUserDetailDelegate() {
		return userDetailDelegate;
	}

	public void setUserDetailDelegate(UserDetailDelegate<UserDetails> userDetailDelegate) {
		this.userDetailDelegate = userDetailDelegate;
	}

}
