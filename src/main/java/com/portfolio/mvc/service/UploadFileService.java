package com.portfolio.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.mvc.domain.UploadFile;
import com.portfolio.mvc.parameter.UploadFileParameter;
import com.portfolio.mvc.repository.IUploadFileRepository;

/**
 * 업로드 파일 서비스
 * @author haewon
 *
 */
@Service
public class UploadFileService {
	
	@Autowired
	private IUploadFileRepository repository;
	
	/**
	 * 등록처리
	 */
	public void save(UploadFileParameter parameter) {
		repository.save(parameter);
	}

	public UploadFile get(int uploadFileSeq) {
		return repository.get(uploadFileSeq);		
	}

}
