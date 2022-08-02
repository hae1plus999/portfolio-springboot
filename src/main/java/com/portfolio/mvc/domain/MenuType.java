package com.portfolio.mvc.domain;

/**
 * 메뉴 종류
 * @author haewon
 *
 */
public enum MenuType {

	community(BoardType.COMMUNITY, "커뮤니티", "/community"),
	notice(BoardType.NOTICE, "공지사항", "/notice"),
	faq(BoardType.FAQ, "자주묻는질문", "/faq"),
	inquiry(BoardType.INQUIRY, "1:1문의", "/inquiry"),
	;

	private BoardType boardType;
	private String menuCode;
	private String url;

	MenuType(BoardType boardType, String menuCode, String url) {
		this.boardType = boardType;
		this.menuCode = menuCode;
		this.url = url;
	}
	
	public BoardType boardType() {
		return boardType;
	}
	
	public String menuCode() {
		return menuCode;
	}
	
	public String url() {
		return url;
	}
}
