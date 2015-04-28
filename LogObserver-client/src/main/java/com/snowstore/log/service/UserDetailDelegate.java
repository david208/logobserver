package com.snowstore.log.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.snowstore.log.vo.UserInfo;

public interface UserDetailDelegate<T extends UserDetails> {

	UserInfo getUserInfo();

}
