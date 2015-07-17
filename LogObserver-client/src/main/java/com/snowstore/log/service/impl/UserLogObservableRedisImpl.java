package com.snowstore.log.service.impl;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.snowstore.log.service.UserLogObservable;
import com.snowstore.log.vo.FileInfo;
import com.snowstore.log.vo.UserInfo;
import com.snowstore.log.vo.UserLogEsVo;

public class UserLogObservableRedisImpl implements UserLogObservable {

	@Autowired
	private RedisTemplate<String, UserLogEsVo> redisTemplate;

	 static final String CHANNEL_NAME = "userLog";

	private String systemCode;

	private final ExecutorService cachedThreadPool = Executors.newFixedThreadPool(4);

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	@Override
	public void notifyObserver(UserInfo userInfo, String remark, String result, String arg, Date logTime, String ip, FileInfo fileInfo,long duration) {
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
		esVo.setDuration(duration);
		cachedThreadPool.submit(new LogTask(esVo, fileInfo, redisTemplate));
	}

}

class LogTask implements Runnable {

	private UserLogEsVo esVo;

	private FileInfo fileInfo;

	private RedisTemplate<String, UserLogEsVo> redisTemplate;

	public LogTask(UserLogEsVo esVo, FileInfo fileInfo, RedisTemplate<String, UserLogEsVo> redisTemplate) {
		this.esVo = esVo;
		this.fileInfo = fileInfo;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void run() {
		if (null != this.fileInfo) {
			UserLogEsVo.File file = new UserLogEsVo.File();
			file.setFileContent(Base64.encodeBase64String(fileInfo.getContent()));
			file.setFileName(fileInfo.getFileName());
			file.setFileType(fileInfo.getContentType());
			esVo.setFile(file);
		}
		try {
			redisTemplate.convertAndSend(UserLogObservableRedisImpl.CHANNEL_NAME, esVo);
		} finally {
		}

	}

	public UserLogEsVo getEsVo() {
		return esVo;
	}

	public void setEsVo(UserLogEsVo esVo) {
		this.esVo = esVo;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

}
