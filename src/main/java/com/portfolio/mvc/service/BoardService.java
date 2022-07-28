package com.portfolio.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.portfolio.mvc.domain.Board;
import com.portfolio.mvc.parameter.BoardParameter;
import com.portfolio.mvc.repository.IBoardRepository;

/**
 * 게시판 서비스 
 * @author haewon
 */
@Service
public class BoardService {

	@Autowired
	private IBoardRepository repository;
	
	/**
	 * 목록 리턴 
	 * @return
	 */
	public List<Board> getList() {
		return repository.getList();
	}
	
	/**
	 * 상세 정보 리턴. 
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	
	/**
	 * 등록/수정 처리.
	 * @param board
	 */
	public void save(BoardParameter parameter) {
		//조회하여 리턴된 정보 
		Board board = repository.get(parameter.getBoardSeq());
		if (board == null) {
			repository.save(parameter);
		} else {
			repository.update(parameter);
		}
	}

	/**
	 * 삭제 처리.
	 * @param boardSeq
	 */
	public boolean delete(int boardSeq) {
		repository.delete(boardSeq);
		return true;
	}
}
