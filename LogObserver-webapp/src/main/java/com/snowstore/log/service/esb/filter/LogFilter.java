package com.snowstore.log.service.esb.filter;

import com.zendaimoney.hera.connector.vo.Datagram;

/**
 * @description: 过滤器
 * @author sm
 */
public interface LogFilter {

	/**
	 * @description: 过滤
	 * @author sm
	 * @param datagram
	 *            报文
	 * @param apolloFilterChain
	 *            过滤链
	 */
	void doFilter(Datagram datagram, LogFilterChain logFilterChain);

}
