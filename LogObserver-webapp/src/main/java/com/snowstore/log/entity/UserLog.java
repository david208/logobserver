package com.snowstore.log.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserLog extends AuditableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String remark;// 备注
	private Long userId;// 用户
	private String arg;// 参数
	private String result;// 结果
	@Indexed()
	private String username;// 用户名
	private boolean ucFlag;// uc标志
	private String systemCode;//
	@TextIndexed
	private String jsonString;// json后字符串
	private Date logTime;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getUcFlag() {
		return ucFlag;
	}

	public void setUcFlag(boolean ucFlag) {
		this.ucFlag = ucFlag;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}
