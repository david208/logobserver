package com.snowstore.log.configuer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "esb")
public class EsbSettings {

	private String esbUrl;

	private String systemCode;

	private Long timeOut;

	public String getEsbUrl() {
		return esbUrl;
	}

	public void setEsbUrl(String esbUrl) {
		this.esbUrl = esbUrl;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public Long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}

}
