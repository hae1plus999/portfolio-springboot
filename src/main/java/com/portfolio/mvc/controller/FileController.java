package com.portfolio.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.configuration.GlobalConfig;
import com.portfolio.configuration.exception.BaseException;
import com.portfolio.configuration.http.BaseResponse;
import com.portfolio.configuration.http.BaseResponseCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GlobalConfig config;
	
	/**
	 * 업로드 리턴
	 * @return
	 */
	@PostMapping("/save")
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
		logger.debug("config : {}", config);
		//필수 널체크
		if(multipartFile == null || multipartFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);
		}
		String uploadFilePath = config.getUploadFilePath();
		logger.debug("uploadFilePath : {}", uploadFilePath);
		if(config.isProd()) {
			logger.debug("isProd calendar : {}", Calendar.getInstance());
		}
		//파일 저장
		//확장자
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
		//저장될 파일명 + 확장자
		String filename = UUID.randomUUID().toString() + "." + prefix;
		//파일 폴더 만들어주기
		File folder = new File(uploadFilePath);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		//파일이 저장될 상위경로 + 파일명
		String pathname = uploadFilePath + filename;
		//파일이 저장될 경로를 지정
		File dest = new File(pathname);
		
		try {
			multipartFile.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			logger.error("e", e);
		} 
		
		return new BaseResponse<Boolean>(true);
	}
}
