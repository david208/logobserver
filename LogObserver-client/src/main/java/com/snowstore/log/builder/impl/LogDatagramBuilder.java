package com.snowstore.log.builder.impl;

import com.snowstore.log.builder.DatagramBuilder;
import com.zendaimoney.hera.connector.vo.DatagramHeader;

/**
 * 
 * @description: log
 * 
 * @author: sm
 */

public class LogDatagramBuilder extends DatagramBuilder {

	private String logSystemCode;

	private String systemCode;

	public String getLogSystemCode() {
		return logSystemCode;
	}

	public void setLogSystemCode(String logSystemCode) {
		this.logSystemCode = logSystemCode;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * @description 修改log报文头
	 * @param datagramHeader
	 *            报文头
	 * 
	 * @author sm
	 */
	@Override
	public DatagramHeader updateDatagramHeader(DatagramHeader datagramHeader) {
		datagramHeader.setSenderSystemCode(systemCode);
		datagramHeader.setReceiverSystem(logSystemCode);
		datagramHeader.setUserName("logObservable");
		datagramHeader.setPassword("123456");
		datagramHeader.setMessageSequence("1");
		return datagramHeader;
	}

}
