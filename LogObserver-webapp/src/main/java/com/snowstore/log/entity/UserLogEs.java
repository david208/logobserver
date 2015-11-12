package com.snowstore.log.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user_action_log", type = "userlog")
public class UserLogEs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	protected String id;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String remark;// 备注
	@Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
	private Long userId;// 用户
	@Field(type = FieldType.String, index = FieldIndex.analyzed)
	private String arg;// 参数
	@Field(type = FieldType.String, index = FieldIndex.analyzed)
	private String result;// 结果
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String username;// 用户名
	@Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
	private boolean ucFlag;// uc标志
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String systemCode;//
	@Field(type = FieldType.Date)
	private Date logTime;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String ip;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String fileId;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String appName;
	@Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
	private boolean fileFlag = false;
	@Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
	private long duration;
	@Field(type = FieldType.String, index = FieldIndex.analyzed)
	private String signature;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public boolean isFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(boolean fileFlag) {
		this.fileFlag = fileFlag;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
