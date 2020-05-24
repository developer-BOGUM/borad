package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.BoardDto;
import board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
//		현재 글의 뷰 수를 업데이트 해줌
//		boardMapper의 updateHitCount() 메서드를 실행
		boardMapper.updateHitCount(boardIdx);
		
//		지정한 글번호의 게시글 정보를 가져옴
//		BoardDto 타입의 객체에 저장
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		return board; // 저장한 정보를 반환
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
}
