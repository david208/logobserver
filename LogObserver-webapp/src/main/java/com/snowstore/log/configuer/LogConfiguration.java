package com.snowstore.log.configuer;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.snowstore.log.configure.LogStashConfigure;
import com.snowstore.log.service.LogAuditor;
import com.zendaimoney.hera.connector.EsbConnector;
import com.zendaimoney.hera.connector.MessageReceiver;

@Configuration
@EnableConfigurationProperties(EsbSettings.class)
@EnableMongoAuditing
@EnableRedisHttpSession
@ComponentScan(basePackages = "com.snowstore.log", excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = LogStashConfigure.class) })
public class LogConfiguration {

	private static final String CHANNLE_NAME = "userLog";
	@Autowired
	private EsbSettings esbSettings;
	@Autowired
	private MessageReceiver logReceiver;
	/*@Autowired
	private LogNofityObserver logNofityObserver;*/

	@Bean
	public Validator getValidator() {
		return new LocalValidatorFactoryBean();
	}

	/*
	 * @Bean public ZooL getZooL() { ZooL zooL =new ZooL();
	 * zooL.setNofityObserver(logNofityObserver); return zooL; }
	 */

	//@Bean
	@Deprecated
	public EsbConnector getEsbConnector() {
		EsbConnector esbConnector = new EsbConnector();
		esbConnector.setEsbUrl(esbSettings.getEsbUrl());
		esbConnector.setMessageReceiver(logReceiver);
		esbConnector.setSystemCode(esbSettings.getSystemCode());
		return esbConnector;
	}

	@Bean
	public AuditorAware<Long> myAuditorProvider() {
		return new LogAuditor();
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		container.addMessageListener(messageListenerAdapter(), new ChannelTopic(CHANNLE_NAME));
		return container;
	}

	@Autowired
	MessageListener userLogMessageDelegate;
	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(userLogMessageDelegate);
		messageListenerAdapter.setSerializer(new JdkSerializationRedisSerializer());
		return messageListenerAdapter;
	}

}
