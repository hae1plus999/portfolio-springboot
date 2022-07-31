package com.portfolio.mvc.domain;

import java.util.Date;

import lombok.Data;

/**
 * 게시물 
 * @author haewon
 *
 */
@Data
public class Board {

	private int boardSeq;
	private BoardType boardType; //Enum
	private String title;
	private String contents;
	private Date reqDate;
	private boolean delYn;
}
