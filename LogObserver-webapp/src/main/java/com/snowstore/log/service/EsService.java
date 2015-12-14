package com.snowstore.log.service;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snowstore.log.entity.UserLogEs;

@Service
@Transactional
public class EsService {

	@Autowired
	TransportClient client;

	private static final Logger LOGGER = LoggerFactory.getLogger(EsService.class);

	public void saveUserLog(UserLogEs userLog) {

		try {
			client.prepareIndex("user_action_log", "userlog")
					.setSource(
							jsonBuilder().startObject().field("appName", userLog.getAppName()).field("arg", userLog.getArg()).field("duration", userLog.getDuration()).field("fileFlag", userLog.isFileFlag()).field("fileId", userLog.getFileId()).field("ip", userLog.getIp())
									.field("logTime", userLog.getLogTime()).field("remark", userLog.getRemark()).field("result", userLog.getResult()).field("signature", userLog.getSignature()).field("systemCode", userLog.getSystemCode()).field("ucFlag", userLog.getUcFlag())
									.field("userId", userLog.getUserId()).field("username", userLog.getUsername()).endObject()).execute();
		} catch (IOException e) {
			LOGGER.error("保存失败", e);
		}
	}

}
