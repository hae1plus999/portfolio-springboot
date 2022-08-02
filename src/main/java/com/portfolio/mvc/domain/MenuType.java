package com.portfolio.mvc.domain;

/**
 * 메뉴 종류
 * @author haewon
 *
 */
public enum MenuType {

	community(BoardType.COMMUNITY),
	notice(BoardType.NOTICE),
	faq(BoardType.FAQ),
	inquiry(BoardType.INQUIRY),
	;

	private BoardType boardType;

	MenuType(BoardType boardType) {
		this.boardType = boardType;
	}
}
