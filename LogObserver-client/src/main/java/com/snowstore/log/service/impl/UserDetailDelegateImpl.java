package com.snowstore.log.service.impl;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.snowstore.log.service.UserDetailDelegate;
import com.snowstore.log.vo.UserInfo;
import com.zendaimoney.uc.rmi.vo.Staff;

/**
 * @description: 用户信息获取
 * @author sm
 */

public class UserDetailDelegateImpl<T extends UserDetails> implements UserDetailDelegate<T> {

	public final static String ANONYMOUS_USER = "anonymousUser";

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailDelegateImpl.class);

	/**
	 * @author SM
	 * @return
	 * @description 取得本地用户的钩子
	 */

	public T getLocalUser() {
		return null;
	}

	/**
	 * @author SM
	 * @description 取得UC用户
	 */
	public Staff getUcStaff() {
		Authentication currentuser = getAuthentication();
		if (checkUser(currentuser) && currentuser.getPrincipal() instanceof Staff) {
			return (Staff) currentuser.getPrincipal();
		}
		return null;
	}

	/**
	 * @author SM
	 * @description 检查客户是否存在且非匿名
	 */
	private boolean checkUser(Authentication currentuser) {
		return null != currentuser && !currentuser.getPrincipal().equals(ANONYMOUS_USER);
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();

	}

	@Override
	public UserInfo getUserInfo() {
		T user = getLocalUser();
		UserInfo userInfo = new UserInfo();
		if (null != user) {
			userInfo.setUcFlag(false);
			userInfo.setUserName(user.getUsername());
			try {
				Method method = user.getClass().getMethod("getId");
				method.setAccessible(true);
				userInfo.setUserId(Long.valueOf(method.invoke(user).toString()));
			} catch (Exception e) {
				LOGGER.warn("获取用户信息出错", e);
				return null;
			}

			return userInfo;
		}
		Staff staff = getUcStaff();
		if (null != staff) {
			userInfo.setUcFlag(true);
			userInfo.setUserName(staff.getUsername());
			userInfo.setUserId(staff.getId());
			return userInfo;
		}
		return null;

	}
}
