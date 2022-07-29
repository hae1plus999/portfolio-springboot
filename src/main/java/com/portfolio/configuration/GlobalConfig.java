package com.portfolio.configuration;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GlobalConfig {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private String uploadFilePath;
	
	@PostConstruct
	public void init() {
		logger.info("init");
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		String activeProfile = "local";
		if(ObjectUtils.isNotEmpty(activeProfiles)) {
			activeProfile = activeProfiles[0];
		}
		//프로퍼티 파일을 읽어오기위해 경로를 만듬
		String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfile);
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			uploadFilePath = properties.getProperty("uploadFile.path");
		} catch (Exception e) {
			logger.error("e", e);
		}
	}
	
	//외부에서 접근할 수 있도록 메소드만듬
	public String getUploadFilePath() {
		return uploadFilePath;
	}

}
