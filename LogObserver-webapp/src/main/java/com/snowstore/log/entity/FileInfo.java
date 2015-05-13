package com.snowstore.log.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FileInfo extends AuditableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileInfo() {
	}

	public FileInfo(String fileName, String contentType) {

		this.fileName = fileName;
		this.contentType = contentType;

	}

	private String fileName;
	private String contentType;
	@Transient
	private byte[] content;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
