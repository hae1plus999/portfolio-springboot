package com.portfolio.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.configuration.exception.BaseException;
import com.portfolio.configuration.framework.web.bind.annotation.RequestConfig;
import com.portfolio.configuration.http.BaseResponse;
import com.portfolio.configuration.http.BaseResponseCode;
import com.portfolio.framework.data.domain.MySQLPageRequest;
import com.portfolio.framework.data.domain.PageRequestParameter;
import com.portfolio.mvc.domain.Board;
import com.portfolio.mvc.domain.MenuType;
import com.portfolio.mvc.parameter.BoardParameter;
import com.portfolio.mvc.parameter.BoardSearchParameter;
import com.portfolio.mvc.repository.IBoardRepository;
import com.portfolio.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 게시판 컨트롤러.
 * @author haewon
 */
@Controller
//@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BoardService boardService;
	
	/**
	 * 목록 리턴 
	 * @return
	 */
	//@Apiparam은 Swagger에서 제공하는 어노테이션, 파라메터에 대한 주석(설명)이나 옵션을 설정
	@GetMapping("/{menuType}")
	public String list(@PathVariable MenuType menuType, BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model) {
		logger.info("menuType : {}", menuType);
		logger.info("pageRequest : {}", pageRequest);
		logger.info("menuType.boardType() : {}", menuType.boardType());
		
		parameter.setBoardType(menuType.boardType());
		logger.info("parameter.getBoardType() : {}", parameter.getBoardType());
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
		List<Board> boardList = boardService.getList(pageRequestParameter);
		model.addAttribute("boardList", boardList);
		model.addAttribute("menuType", menuType);
		return "/board/list";
	}
	
	//@Apiparam은 Swagger에서 제공하는 어노테이션, 파라메터에 대한 주석(설명)이나 옵션을 설정
//	@GetMapping
//	@ResponseBody
//	@ApiOperation(value = "목록 조회",notes = "게시물 목록 정보를 조회할 수 있습니다.")
//	public BaseResponse<List<Board>> getList(
//			@ApiParam BoardSearchParameter parameter,
//			@ApiParam MySQLPageRequest pageRequest) {
//		
//		logger.info("pageRequest : {}", pageRequest);
//		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
//		return new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
//	}
	
	/**
	 * 상세 정보 화면
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{menuType}/{boardSeq}")
	public String detail(@PathVariable MenuType menuType, @PathVariable int boardSeq, Model model) {
		Board board = boardService.get(boardSeq);
		//null 처리
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
		}
		model.addAttribute("board", board);
		model.addAttribute("menuType", menuType);
		return "/board/detail";
	}
	
//	@GetMapping("/{boardSeq}")
//	@ResponseBody
//	@ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
//	})
//	public BaseResponse<Board> get(@PathVariable int boardSeq) {
//		Board board = boardService.get(boardSeq);
//		//null 처리
//		if(board == null) {
//			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
//		}
//		return new BaseResponse<Board>(board);
//	}
	
	/**
	 * 등록 화면
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/{menuType}/form")
	@RequestConfig(loginCheck = false)
	public String form(@PathVariable MenuType menuType, BoardParameter parameter, Model model) {
		model.addAttribute("parameter", parameter);
		model.addAttribute("menuType", menuType);
		return "/board/form";
	}
	
	/**
	 * 수정 화면
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/{menuType}/edit/{boardSeq}")
	@RequestConfig(loginCheck = false)
	public String edit(@PathVariable MenuType menuType, @PathVariable(required = true) int boardSeq, BoardParameter parameter, Model model) {
		
		Board board = boardService.get(parameter.getBoardSeq());
		//null 처리
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
		}
		model.addAttribute("board", board);
		model.addAttribute("parameter", parameter);
		model.addAttribute("menuType", menuType);
		return "board/form";
	}
	
	/**
	 * 등록/수정 처리.
	 * @param board
	 */
	@PostMapping("/{menuType}/save")
	@RequestConfig(loginCheck = false)
	@ResponseBody
	@ApiOperation(value = "등록/수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "boardType", value = "게시판 종류", example = "NOTICE"),
		@ApiImplicitParam(name = "title", value = "제목", example = "spring"),
		@ApiImplicitParam(name = "contents", value = "내용", example = "spring 강좌")
	})
	public BaseResponse<Integer> save(@PathVariable MenuType menuType, BoardParameter parameter) {
		//제목 필수 체크 
		if(StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목" });
		}
		//내용 필수 체크 
		if(StringUtils.isEmpty(parameter.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "내용" });
		}
		parameter.setBoardType(menuType.boardType());
		boardService.save(parameter);
		return new BaseResponse<Integer>(parameter.getBoardSeq());
	}
	
	/**
	 * 대용량 등록 처리.
	 */
	@ApiOperation(value = "대용량 등록처리1", notes = "대용량 등록처리1")
	@PutMapping("/saveList1")
	public BaseResponse<Boolean> saveList1() {
		int count = 0;
		// 테스트를 위한 랜덤 1만건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while (true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if (count >= 10000) {
				break;
			}
		}
		//시간 측정 
		long start = System.currentTimeMillis();
		boardService.saveList1(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}

	/**
	 * 대용량 등록 처리.
	 */
	@PutMapping("/saveList2")
	@ApiOperation(value = "대용량 등록처리2", notes = "대용량 등록처리2")
	public BaseResponse<Boolean> saveList2() {
		int count = 0;
		// 테스트를 위한 랜덤 1만건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while (true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if (count >= 10000) {
				break;
			}
		}
		//시간 측정 
		long start = System.currentTimeMillis();
		boardService.saveList2(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}
	
	/**
	 * 삭제 처리.
	 * @param boardSeq
	 */
	@DeleteMapping("/delete/{boardSeq}")
	@RequestConfig
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
	})
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
}
