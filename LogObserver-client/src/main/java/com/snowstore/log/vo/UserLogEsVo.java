package com.snowstore.log.vo;

import java.io.Serializable;
import java.util.Date;

//日志记录
public class UserLogEsVo implements Serializable {

	public static class File implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String fileContent;
		private String fileName;
		private String fileType;

		public File() {
		}

		public String getFileContent() {
			return fileContent;
		}

		public void setFileContent(String fileContent) {
			this.fileContent = fileContent;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String username;
	private String remark;
	private String result;
	private String arg;
	private boolean ucFlag;
	private Date logTime;
	private String ip;
	private File file;
	private String appName;
	private long duration;
	private String signature;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public boolean isUcFlag() {
		return ucFlag;
	}

	public void setUcFlag(boolean ucFlag) {
		this.ucFlag = ucFlag;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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
