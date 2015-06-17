package com.snowstore.log.vo;

public class UserLogVo extends PageFormVo {

	private String type = "true";

	private String keyword;

	private Long refreshTime = 10000l;
	
	private String systemCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Long refreshTime) {
		this.refreshTime = refreshTime;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
}
