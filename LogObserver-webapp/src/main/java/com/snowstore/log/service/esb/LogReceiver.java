package com.snowstore.log.service.esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zendaimoney.hera.connector.MessageReceiver;
import com.zendaimoney.hera.connector.vo.Datagram;

/**
 * @description: 接口监听
 * 
 * @author sm
 */
@Component
public class LogReceiver implements MessageReceiver {

	@Autowired
	private EsbService esbService;

	@Override
	public Datagram receive(Datagram datagram) {
		return esbService.handle(datagram);

	}
}
