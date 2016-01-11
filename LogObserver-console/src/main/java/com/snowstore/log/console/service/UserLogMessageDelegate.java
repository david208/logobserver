package com.snowstore.log.console.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.snowstore.log.console.vo.JavaLog;

@Component
public class UserLogMessageDelegate implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLogMessageDelegate.class);

	Gson gson = new Gson();

	@Autowired
	private BroadcastService broadcastService;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			JavaLog javaLog = gson.fromJson(new String(message.getBody()), JavaLog.class);
			broadcastService.broadcast(javaLog.getMessage(), javaLog.getApp_name());
		} catch (Exception e) {
			LOGGER.error("广播日志出错", e);
		}

	}

}
