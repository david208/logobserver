package com.snowstore.log.builder;

import com.zendaimoney.hera.connector.vo.Datagram;
import com.zendaimoney.hera.connector.vo.DatagramBody;
import com.zendaimoney.hera.connector.vo.DatagramHeader;

/**
 * 
 * @description: 报文工厂
 * 
 * @author: sm
 */
public abstract class DatagramBuilder {
	/**
	 * 
	 * @description 根据消息对象和消息编码创建报文
	 * 
	 * @param datagrambody
	 *            消息对象
	 * @param messageCode
	 *            消息编码
	 * @return 返回创建的报文
	 * 
	 * @author sm
	 */
	public final Datagram createDatagram(DatagramBody datagramBody, String messageCode) {
		Datagram datagram = new Datagram();

		// 创建消息头
		DatagramHeader datagramHeader = new DatagramHeader();
		// 修改消息头
		datagramHeader.setMessageCode(messageCode);
		datagram.setDatagramHeader(updateDatagramHeader(datagramHeader));
		// 修改消息体
		datagram.setDatagramBody(updateDatagramBody(datagramBody));

		return datagram;

	}

	/**
	 * 
	 * @description 修改报文体
	 * 
	 * @param datagramBody
	 *            报文体
	 * @return 报文体
	 * 
	 * @author sm
	 */
	public DatagramBody updateDatagramBody(DatagramBody datagramBody) {
		return datagramBody;
	}

	/**
	 * 
	 * @description 修改报文头
	 * 
	 * @param datagramHeader
	 *            报文头
	 * @return 报文头
	 * 
	 * @author sm
	 */
	public DatagramHeader updateDatagramHeader(DatagramHeader datagramHeader) {
		return datagramHeader;
	}

}
