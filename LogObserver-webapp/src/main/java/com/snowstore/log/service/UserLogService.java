package com.snowstore.log.service;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.snowstore.hera.connector.vo.logObserver.D100001;
import com.snowstore.log.entity.UserLog;
import com.snowstore.log.repository.UserLogRepository;
import com.snowstore.log.vo.UserLogVo;

@Service
@Transactional
public class UserLogService {

	private static final Mapper mapper = new DozerBeanMapper();

	private static final Gson gson = new Gson();

	@Autowired
	private UserLogRepository userLogRepository;

	public Page<UserLog> findPage(final UserLogVo formVo) {
		if (StringUtils.isEmpty(formVo.getUsername()))
			return userLogRepository.findAll(formVo);
		else
			return userLogRepository.findByJsonStringLike(formVo.getUsername(), formVo);

	}

	/**
	 * 异步保存用户操作日志
	 * 
	 * @author sm
	 * @param userId
	 *            用户id
	 * @param remark
	 *            备注
	 * @param result
	 *            结果
	 * @param arg
	 *            参数
	 * @param ucFlag
	 *            uc用户标志
	 */
	public void saveUserLog(D100001 d100001, String systemCode) {
		UserLog userLog = new UserLog();
		mapper.map(d100001, userLog);
		userLog.setSystemCode(systemCode);
		userLog.setJsonString(gson.toJson(userLog));
		userLogRepository.save(userLog);
	}

}
