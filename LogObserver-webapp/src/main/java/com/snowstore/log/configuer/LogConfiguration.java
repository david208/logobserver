package com.snowstore.log.configuer;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.snowstore.hera.connector.monitor.impl.ZooL;
import com.snowstore.log.service.LogAuditor;
import com.snowstore.log.service.LogNofityObserver;
import com.zendaimoney.hera.connector.EsbConnector;
import com.zendaimoney.hera.connector.MessageReceiver;

@Configuration
@EnableConfigurationProperties(EsbSettings.class)
@EnableMongoAuditing
@ComponentScan("com.snowstore.log")
public class LogConfiguration {

	@Autowired
	private EsbSettings esbSettings;
	@Autowired
	private MessageReceiver logReceiver;
	@Autowired
	private LogNofityObserver logNofityObserver;

	@Bean
	public Validator getValidator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ZooL getZooL() {
		ZooL zooL =new ZooL();
		zooL.setNofityObserver(logNofityObserver);
		return zooL;
	}

	@Bean
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

}
