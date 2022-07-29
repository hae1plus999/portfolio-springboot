package com.portfolio.mvc.parameter;

import java.util.Date;

import com.portfolio.mvc.domain.BoardType;

import lombok.Data;

/**
 * 게시물 파라미터
 * @author haewon
 *
 */
@Data
public class BoardParameter {

	private int boardSeq;
	private BoardType boardType; //Enum
	private String title;
	private String contents;

	public BoardParameter() {
		
	}
	
	/**
	 * 테스트용 생성자 
	 * @param title
	 * @param contents
	 */
	public BoardParameter(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
}
