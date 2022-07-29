package com.portfolio.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.portfolio.mvc.domain.Board;
import com.portfolio.mvc.parameter.BoardParameter;
import com.portfolio.mvc.parameter.BoardSearchParameter;
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
	public List<Board> getList(BoardSearchParameter parameter) {
		return repository.getList(parameter);
	}
	
	/**
	 * 단순 반복문을 이용한 등록 처리.100개 이하에서 권장 
	 */
	public void saveList1(List<BoardParameter> list) {
		//1개가 생성될때마다 레파지토리 호출 =connection매번 호출 
		for (BoardParameter parameter : list) {
			repository.save(parameter);
		}
	}
	
	/**
	 * 100씩 배열에 담아서 일괄 등록 처리.
	 */
	public void saveList2(List<BoardParameter> boardList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//map에 리스트(1만건) 한번에 담아서 총 connection 1번 호출 
		paramMap.put("boardList", boardList);
		repository.saveList(paramMap);
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
