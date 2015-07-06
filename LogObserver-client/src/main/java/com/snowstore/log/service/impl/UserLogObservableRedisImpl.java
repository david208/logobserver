package com.snowstore.log.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.scheduling.annotation.Async;

import com.snowstore.log.service.UserLogObservable;
import com.snowstore.log.vo.FileInfo;
import com.snowstore.log.vo.UserInfo;
import com.snowstore.log.vo.UserLogEsVo;

public class UserLogObservableRedisImpl implements UserLogObservable {

	@Autowired
	private RedisTemplate<String, UserLogEsVo> redisTemplate;

	private String systemCode;

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	@Override
	@Async
	public void notifyObserver(UserInfo userInfo, String remark, String result, String arg, Date logTime, String ip, FileInfo fileInfo) {
		UserLogEsVo esVo = new UserLogEsVo();
		esVo.setAppName(systemCode);
		esVo.setArg(arg);
		esVo.setLogTime(logTime);
		esVo.setIp(ip);
		esVo.setRemark(remark);
		esVo.setResult(result);
		esVo.setUcFlag(userInfo.isUcFlag());
		esVo.setUserId(userInfo.getUserId());
		esVo.setUsername(userInfo.getUserName());
		if (null != fileInfo) {
			UserLogEsVo.File file = new UserLogEsVo.File();
			file.setFileContent(Base64.encodeBase64String(fileInfo.getContent()));
			file.setFileName(fileInfo.getFileName());
			file.setFileType(fileInfo.getContentType());
			esVo.setFile(file);
		}
		try {
			redisTemplate.convertAndSend("userLog", esVo);
		} finally {
		}

	}

}
