package com.snowstore.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snowstore.hera.connector.monitor.impl.NofityObserver;
import com.snowstore.log.ws.BroadcastService;

@Component
public class LogNofityObserver implements NofityObserver {

	@Autowired
	private BroadcastService broadcastService;

	@Override
	public void notifyDo() {
		broadcastService.broadcast("changed");

	}

}
