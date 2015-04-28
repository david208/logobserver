package com.snowstore.log.vo;

public class UserInfo {

	private Long userId;

	private String userName;

	private boolean ucFlag;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isUcFlag() {
		return ucFlag;
	}

	public void setUcFlag(boolean ucFlag) {
		this.ucFlag = ucFlag;
	}

}
