package com.portfolio.mvc.parameter;

import java.util.List;

import com.portfolio.mvc.domain.BoardType;

import lombok.Data;

/**
 * 게시판 검색 파라미터
 * @author haewon
 */
@Data
public class BoardSearchParameter {
	
	private String keyword;
	private BoardType[] boardTypes;
	
	public BoardSearchParameter() {
		
	}
}
