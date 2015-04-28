package com.snowstore.log.service.esb.handle.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snowstore.hera.connector.vo.logObserver.D100001;
import com.snowstore.log.service.UserLogService;
import com.snowstore.log.service.esb.handle.LogHandler;
import com.zendaimoney.hera.connector.vo.DatagramBody;

@Service
@Transactional
public class H100001 implements LogHandler {

	@Autowired
	private UserLogService userLogService;

	@Override
	public DatagramBody handle(DatagramBody datagramBody, String systemCode) {
		userLogService.saveUserLog((D100001) datagramBody, systemCode);
		return datagramBody;
	}

}
