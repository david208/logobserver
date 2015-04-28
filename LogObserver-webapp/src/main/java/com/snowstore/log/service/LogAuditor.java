package com.snowstore.log.service;

import org.springframework.data.domain.AuditorAware;

public class LogAuditor implements AuditorAware<Long> {

	@Override
	public Long getCurrentAuditor() {
		return null;
	}
}
