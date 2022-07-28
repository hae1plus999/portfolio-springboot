package com.portfolio.configuration.http;

import lombok.Data;

/**
 * 공통으로 사용할 클래스 
 * @author haewon
 * @param <T>
 */
@Data
public class BaseResponse<T> {

	private BaseResponseCode code;
	private String message;
	private T data;
	
	/**
	 * 성공처리
	 * @param data
	 */
	public BaseResponse(T data) {
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
	}
	
	/**
	 * 예외처리 
	 * @param code
	 * @param message
	 */
	public BaseResponse(BaseResponseCode code, String message) {
		this.code = code;
		this.message = message;
	}
}
