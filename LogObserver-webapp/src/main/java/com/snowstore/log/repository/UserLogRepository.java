package com.snowstore.log.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.snowstore.log.entity.UserLog;

public interface UserLogRepository extends MongoRepository<UserLog, String> {

	Page<UserLog> findByUsername(String username, Pageable pageable);

	Page<UserLog> findByJsonStringLikeAndSystemCode(String jsonString, String systemCode, Pageable pageable);
	
	Page<UserLog> findByJsonStringLike(String jsonString, Pageable pageable);


}
