package com.snowstore.log.service.esb.filter.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.snowstore.log.exception.BusinessException;
import com.snowstore.log.service.esb.filter.LogFilter;
import com.snowstore.log.service.esb.filter.LogFilterChain;
import com.zendaimoney.hera.connector.vo.Datagram;

/**
 * @description: jsr303校验过滤器
 * @author sm
 */
@Service
public class Jsr303Filter implements LogFilter {

	private static final String ERROR_CODE = "000001";
	@Autowired
	private Validator validator;

	@Override
	public void doFilter(Datagram datagram, LogFilterChain logFilterChain) {
		Set<ConstraintViolation<Datagram>> constraintViolations = validator.validate(datagram);
		if (!CollectionUtils.isEmpty(constraintViolations)) {
			StringBuilder message = new StringBuilder();
			for (ConstraintViolation<Datagram> constrainViolation : constraintViolations) {
				message.append(constrainViolation.getPropertyPath().toString() + constrainViolation.getMessage() + "|");
			}

			throw new BusinessException(ERROR_CODE, message.toString());
		}
		logFilterChain.doFilter(datagram);

	}
}
