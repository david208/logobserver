package com.snowstore.log.configuer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.snowstore.log.configure.LogStashConfigure;

@Configuration
@ComponentScan(basePackages = "com.snowstore.log", excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = LogStashConfigure.class) })
public class ElasticConfiguration {

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;

	@Bean
	public TransportClient getTransportClient() throws UnknownHostException {
		Settings settings = Settings.settingsBuilder().put("client.transport.sniff", true).put("cluster.name", "yilianlog").build();
		String[] nodes = clusterNodes.split(",");
		TransportClient client = TransportClient.builder().settings(settings).build();
		for (String node : nodes) {
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(StringUtils.substringBefore(node, ":")), Integer.valueOf(StringUtils.substringAfter(node, ":"))));
		}
		return client;

	}

}
