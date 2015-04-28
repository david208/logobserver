package com.snowstore.log.service.esb;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snowstore.log.service.esb.filter.LogFilterChain;
import com.snowstore.log.service.esb.handle.LogHandler;
import com.zendaimoney.hera.connector.vo.Datagram;

/**
 * @description: esb的服务类
 * 
 * @author sm
 */

@Service
public class EsbService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LogFilterChain logFilterChain;

	@Autowired
	private Map<String, LogHandler> logHandlers;

	/**
	 * @description: 处理报文并返回结果
	 * @author sm
	 * @param datagram
	 *            请求报文
	 * @return 返回报文
	 */
	public Datagram handle(Datagram datagram) {
		logger.info("接收到报文:" + datagram);
		String messageCode = datagram.getDatagramHeader().getMessageCode();
		try {
			logFilterChain.doFilter(datagram);
			// 处理请求
			logHandlers.get("h" + messageCode).handle(datagram.getDatagramBody(), datagram.getDatagramHeader().getSenderSystemCode());
		} catch (Exception e) {
			logger.warn(e.toString());
		}
		return datagram;

	}

}
