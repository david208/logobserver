package com.snowstore.log.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.activemq.protobuf.BufferInputStream;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.snowstore.hera.connector.vo.logObserver.D100001;
import com.snowstore.log.entity.FileInfo;
import com.snowstore.log.entity.UserLog;
import com.snowstore.log.repository.FileInfoRepository;
import com.snowstore.log.repository.UserLogRepository;
import com.snowstore.log.vo.UserLogVo;

@Service
@Transactional
public class UserLogService {

	private static final Mapper mapper = new DozerBeanMapper();

	private static final Gson gson = new Gson();

	@Autowired
	private UserLogRepository userLogRepository;

	@Autowired
	private GridFsOperations operations;
	@Autowired
	private FileInfoRepository fileInfoRepository;

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
		if (null != d100001.getFile()) {
			FileInfo fileInfo = new FileInfo(d100001.getFile().getFileName(), d100001.getFile().getFileType());
			fileInfoRepository.save(fileInfo);
			try {
				saveFile(fileInfo, Base64Utils.decodeFromString(d100001.getFile().getFileContent()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			userLog.setFileInfo(fileInfo);
		}
		userLogRepository.save(userLog);
	}

	public void saveFile(FileInfo fileInfo, byte[] content) throws IOException {
		operations.store(new BufferInputStream(content), fileInfo.getFileName(), fileInfo);
	}

	/**
	 * 
	 * @author: sm
	 * @param id
	 *            id
	 * @return 文件内容
	 * @throws IOException
	 */
	public FileInfo getFile(String id) throws IOException {
		FileInfo fileInfo = fileInfoRepository.findOne(id);
		InputStream inputStream = operations.findOne(Query.query(GridFsCriteria.whereMetaData().in(fileInfo))).getInputStream();
		try {
			fileInfo.setContent(ByteStreams.toByteArray(inputStream));
			return fileInfo;
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
	}

}
