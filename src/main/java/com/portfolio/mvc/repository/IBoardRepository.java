package com.portfolio.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.mvc.domain.Board;
import com.portfolio.mvc.parameter.BoardParameter;

/**
 * 게시판 Repository
 * @author haewon
 *
 */
@Repository
public interface IBoardRepository {

	List<Board> getList();
	//조회 
	Board get(int boardSeq);
	//저장 
	int save(BoardParameter board);
	//수정 
	void update(BoardParameter board);
	//삭제 
	void delete(int boardSeq);
}
