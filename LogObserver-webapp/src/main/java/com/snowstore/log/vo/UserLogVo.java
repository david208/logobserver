package com.snowstore.log.vo;

public class UserLogVo extends PageFormVo {

	private String type;

	private String username;
	
	private Long refreshTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Long refreshTime) {
		this.refreshTime = refreshTime;
	}
}
