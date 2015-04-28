package com.snowstore.log.service.esb.handle;

import com.zendaimoney.hera.connector.vo.DatagramBody;

/**
 * @description: 接口处理器
 * 
 * @author sm
 */
public interface LogHandler {

	/**
	 * @description: 处理报文并返回结果
	 * @author sm
	 * @param datagram
	 *            请求报文
	 * @return 返回报文
	 */
	DatagramBody handle(DatagramBody datagramBody, String systemCode);
}