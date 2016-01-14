/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snowstore.log.console.configure;

import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.snowstore.log.console.service.LogWsHandler;

@Configuration
@EnableWebSocket
@EnableRedisHttpSession
public class ConsoleConfigurer implements WebSocketConfigurer {

	@Value("${server.context-path}")
	private String contextPath;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(echoWebSocketHandler(), "/tail").setAllowedOrigins("http://localhost","http://log.jlfex.com").addInterceptors(new HttpSessionHandshakeInterceptor()).setHandshakeHandler(handshakeHandler()).withSockJS().setClientLibraryUrl("js/sockjs.min.js");
	}

	@Bean
	public DefaultHandshakeHandler handshakeHandler() {

		WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
		policy.setInputBufferSize(32768);
		policy.setIdleTimeout(0);

		return new DefaultHandshakeHandler(new JettyRequestUpgradeStrategy(new WebSocketServerFactory(policy)));
	}

	@Bean
	public LogWsHandler echoWebSocketHandler() {
		return new LogWsHandler();
	}

	/*
	 * @Bean public ServerEndpointExporter serverEndpointExporter() { return new
	 * ServerEndpointExporter(); }
	 */

	@Value("${channel.name}")
	private String CHANNLE_NAME = "javalog:redis";

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("redisMessageListenerContainer-");
		asyncTaskExecutor.setConcurrencyLimit(2);
		container.setTaskExecutor(asyncTaskExecutor);
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
