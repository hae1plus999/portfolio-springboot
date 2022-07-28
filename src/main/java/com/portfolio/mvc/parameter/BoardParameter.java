package com.portfolio.mvc.parameter;

import java.util.Date;

import lombok.Data;

/**
 * 게시물 
 * @author haewon
 *
 */
@Data
public class BoardParameter {

	private int boardSeq;
	private String title;
	private String contents;

}
