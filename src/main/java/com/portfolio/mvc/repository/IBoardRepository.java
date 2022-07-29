package com.portfolio.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.framework.data.domain.PageRequestParameter;
import com.portfolio.mvc.domain.Board;
import com.portfolio.mvc.parameter.BoardParameter;
import com.portfolio.mvc.parameter.BoardSearchParameter;

/**
 * 게시판 Repository
 * @author haewon
 *
 */
@Repository
public interface IBoardRepository {

	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
	//조회 
	Board get(int boardSeq);
	//저장 
	int save(BoardParameter board);
	//테스트용 저장 
	void saveList(Map<String, Object> paramMap);
	//수정 
	void update(BoardParameter board);
	//삭제 
	void delete(int boardSeq);
}
