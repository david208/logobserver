package com.snowstore.log.service;

import java.util.Date;

import com.snowstore.log.vo.FileInfo;
import com.snowstore.log.vo.UserInfo;

public interface UserLogObservable {

	// 通知
	void notifyObserver(UserInfo userInfo, String remark, String result, String arg, Date logTime, String ip, FileInfo fileInfo,long duration);

}
