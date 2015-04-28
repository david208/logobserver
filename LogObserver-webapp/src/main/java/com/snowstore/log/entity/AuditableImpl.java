/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.snowstore.log.entity;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * 统一定义顶层Entity实体审计 基类
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 */
// JPA 基类的标识

public abstract class AuditableImpl implements Serializable {
	private static final long serialVersionUID = 141481953116476081L;

	@Id
	protected  String id;

	@Version
	protected  Long version;// 版本

	@CreatedDate
	protected  Date createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
