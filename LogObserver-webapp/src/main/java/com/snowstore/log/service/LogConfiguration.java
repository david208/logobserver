package com.snowstore.log.service;

import java.util.ArrayList;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.snowstore.log.configuer.EsbSettings;
import com.snowstore.log.service.esb.filter.LogFilter;
import com.snowstore.log.service.esb.filter.LogFilterChain;
import com.snowstore.log.service.esb.filter.impl.LogFilterChainImp;
import com.zendaimoney.hera.connector.EsbConnector;
import com.zendaimoney.hera.connector.MessageReceiver;

@Configuration
@EnableConfigurationProperties(EsbSettings.class)
@EnableMongoAuditing
public class LogConfiguration {

	@Autowired
	private EsbSettings esbSettings;
	@Autowired
	private MessageReceiver logReceiver;

	@Autowired
	private LogFilter jsr303Filter;

	@Bean
	public Validator getValidator() {
		return new LocalValidatorFactoryBean();
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
	public LogFilterChain getLogFilterChain() {
		LogFilterChainImp logFilterChain = new LogFilterChainImp();
		ArrayList<LogFilter> filters = new ArrayList<LogFilter>();
		filters.add(jsr303Filter);
		logFilterChain.setFilters(filters);
		return logFilterChain;
	}

	@Bean
	public AuditorAware<Long> myAuditorProvider() {
		return new LogAuditor();
	}

}
