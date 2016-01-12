package com.snowstore.log.console.configure;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snowstore.terra.client.TerraPropertyPlaceholderConfigurer;

@Configuration
public class PropertiesConfiguration {

	@Bean
	public PlaceholderConfigurerSupport getTerraPropertyPlaceholderConfigurer() {
		PlaceholderConfigurerSupport configurerSupport = new TerraPropertyPlaceholderConfigurer();
		return configurerSupport;
	}

}
