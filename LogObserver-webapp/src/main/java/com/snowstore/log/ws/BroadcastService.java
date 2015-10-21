package com.snowstore.log.ws;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class BroadcastService {

	private final Lock lock = new ReentrantLock();

	private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<String, WebSocketSession>();

	public void addSession(WebSocketSession session) {
		lock.lock();
		sessions.put(session.getId(), session);
		lock.unlock();
	}

	private void sendMessage(WebSocketSession session, String msg) throws Exception {
		session.sendMessage(new TextMessage(msg));
	}

	public void broadcast(String message) {
		Collection<WebSocketSession> sessions = new CopyOnWriteArrayList<>(getSession());
		for (WebSocketSession session : sessions) {
			try {
				sendMessage(session, message);
			} catch (Throwable ex) {
				// if Snake#sendMessage fails the client is removed
				removeSession(session);
			}
		}
	}

	private Collection<WebSocketSession> getSession() {
		return Collections.unmodifiableCollection(sessions.values());
	}

	public void removeSession(WebSocketSession session) {
		lock.lock();
		sessions.remove(session.getId());
		lock.unlock();
	}

}
