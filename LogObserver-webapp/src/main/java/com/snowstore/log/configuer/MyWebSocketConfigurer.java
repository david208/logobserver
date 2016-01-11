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

package com.snowstore.log.configuer;

import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.snowstore.log.ws.LogWsHandler;

@Configuration
@EnableWebSocket
public class MyWebSocketConfigurer implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(echoWebSocketHandler(), "/echo").addInterceptors(new HttpSessionHandshakeInterceptor()).setHandshakeHandler(handshakeHandler()).withSockJS().setClientLibraryUrl("js/sockjs.min.js");
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

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
