package com.snowstore.log.console.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class BroadcastService {

	private final Lock lock = new ReentrantLock();

	private final ConcurrentHashMap<String, Set<WebSocketSession>> systemSessionListener = new ConcurrentHashMap<String, Set<WebSocketSession>>();

	// private final ConcurrentHashMap<String, WebSocketSession> sessions = new
	// ConcurrentHashMap<String, WebSocketSession>();

	public void addSession(String systemCode, WebSocketSession session) {
		lock.lock();
		Set<WebSocketSession> systemSessionSet = null;
		if (!systemSessionListener.containsKey(systemCode)) {
			systemSessionSet = new HashSet<WebSocketSession>();
			systemSessionListener.put(systemCode, systemSessionSet);
		} else {
			systemSessionSet = systemSessionListener.get(systemCode);
		}
		systemSessionSet.add(session);
		// sessions.put(session.getId(), session);
		lock.unlock();
	}

	private void sendMessage(WebSocketSession session, String msg) throws Exception {
		session.sendMessage(new TextMessage(msg));
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(BroadcastService.class);

	public synchronized void broadcast(String message, String systemCode) {
		Set<WebSocketSession> webSocketSessions = systemSessionListener.get(systemCode);
		if (null == webSocketSessions)
			return;
		Set<WebSocketSession> closedSessions = null;
		for (WebSocketSession session : webSocketSessions) {
			closedSessions = new HashSet<WebSocketSession>();
			try {
				sendMessage(session, message);
			} catch (Exception ex) {
				closedSessions.add(session);
				LOGGER.error("发消息失败", ex);
			}
		}
		if (null != closedSessions)
			webSocketSessions.removeAll(closedSessions);
	}

}
