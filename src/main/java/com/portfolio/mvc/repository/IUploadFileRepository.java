package com.portfolio.mvc.repository;

import org.springframework.stereotype.Repository;

import com.portfolio.mvc.parameter.UploadFileParameter;

/**
 * 업로드 파일 Repository
 * @author haewon
 *
 */
@Repository
public interface IUploadFileRepository {

	void save(UploadFileParameter parameter);
}
