package com.snowstore.log.ws;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class LogWsHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(LogWsHandler.class);

	@Autowired
	private EchoService echoService;
	@Autowired
	private BroadcastService broadcastService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String echoMessage = echoService.getMessage(message.getPayload());
		logger.info(echoMessage);
		session.sendMessage(new TextMessage(echoMessage));

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		broadcastService.addSession(session);
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		broadcastService.removeSession(session);
		super.afterConnectionClosed(session, status);
	}
	
	

}
