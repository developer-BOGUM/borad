package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception {
		// BoardService 의 insertBoard() 메서드를 이용하여 데이터를 DB에 저장
		boardService.insertBoard(board);
		
		// RequestMapping에 의해 /board/openBoardList.do 와 연결된
		// openBoardList() 메서드를 실행
		return "redirect:/board/openBoardList.do";
	}
	
	// @RequestParam 은 사용자가 입력한 내용을 따로따로 입력하고자 할 경우 사용
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoadDetail(@RequestParam int boardIdx) throws Exception {
		// ModelAndView 선언 시 html 부분 연결
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		// 선택한 글에 대한 정보만 있으면 되기 때문에 List 타입이 아닌 BoardDto 타입으로 데이터를 받아옴
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		// 화면 출력
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception {
		boardService.updateBoard(board);
		
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(@RequestParam int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		
		return "redirect:/board/openBoardList.do";
	}
}












