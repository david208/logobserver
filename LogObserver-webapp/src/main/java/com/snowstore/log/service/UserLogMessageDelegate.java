package com.snowstore.log.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.snowstore.log.vo.UserLogEsVo;

@Component
public class UserLogMessageDelegate implements MessageListener {

	@Autowired
	private UserLogService userLogService;

	private RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			userLogService.saveUserLogEs((UserLogEsVo) redisSerializer.deserialize(message.getBody()));
		} catch (Exception e) {
			logger.error("保存用户日志出错", e);
		}

	}

}
