package com.snowstore.log.service.esb.filter;

import com.zendaimoney.hera.connector.vo.Datagram;

/**
 * @description: 过滤器链
 * @author sm
 */
public interface LogFilterChain {

	/**
	 * @description: 过滤
	 * @author sm
	 * @param datagram
	 *            报文
	 */
	public void doFilter(Datagram datagram);

}
