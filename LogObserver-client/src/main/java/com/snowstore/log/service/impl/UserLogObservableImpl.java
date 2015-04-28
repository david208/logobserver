package com.snowstore.log.service.impl;

import java.util.Date;

import com.snowstore.hera.connector.vo.logObserver.D100001;
import com.snowstore.log.builder.DatagramBuilder;
import com.snowstore.log.service.UserLogObservable;
import com.snowstore.log.vo.UserInfo;
import com.zendaimoney.hera.connector.EsbConnector;
import com.zendaimoney.hera.connector.vo.Datagram;

public class UserLogObservableImpl implements UserLogObservable {

	private DatagramBuilder logDatagramBuilder;

	private EsbConnector esbConnector;

	/**
	 * 异步保存用户操作日志
	 * 
	 * @author sm
	 * @param userInfo
	 *            用户信息
	 * @param remark
	 *            备注
	 * @param result
	 *            结果
	 * @param arg
	 *            参数
	 * @param logTime
	 *            日志时间
	 */
	@Override
	public void notifyObserver(UserInfo userInfo, String remark, String result, String arg, Date logTime) {
		D100001 d100001 = new D100001();
		d100001.setArg(arg);
		d100001.setLogTime(logTime);
		d100001.setRemark(remark);
		d100001.setResult(result);
		d100001.setUcFlag(userInfo.isUcFlag());
		d100001.setUserId(userInfo.getUserId());
		d100001.setUsername(userInfo.getUserName());
		Datagram datagram = logDatagramBuilder.createDatagram(d100001, "100001");
		sendMessageNoAcept(datagram);
	}

	/**
	 * 
	 * @description: 发送过后无返回
	 * @param message
	 *            请求报文
	 * 
	 * @author sm
	 */
	public void sendMessageNoAcept(Datagram message) {
		esbConnector.send(message);
	}

	public DatagramBuilder getLogDatagramBuilder() {
		return logDatagramBuilder;
	}

	public void setLogDatagramBuilder(DatagramBuilder logDatagramBuilder) {
		this.logDatagramBuilder = logDatagramBuilder;
	}

	public EsbConnector getEsbConnector() {
		return esbConnector;
	}

	public void setEsbConnector(EsbConnector esbConnector) {
		this.esbConnector = esbConnector;
	}

}
