package com.snowstore.log.console.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class LogWsHandler extends TextWebSocketHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogWsHandler.class);

	@Autowired
	private BroadcastService broadcastService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String systemCode = message.getPayload();

		LOGGER.info(systemCode);
		broadcastService.addSession(systemCode, session);

	}

}
