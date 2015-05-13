package com.snowstore.log.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snowstore.log.entity.FileInfo;

public interface FileInfoRepository extends MongoRepository<FileInfo, String> {

	

}
