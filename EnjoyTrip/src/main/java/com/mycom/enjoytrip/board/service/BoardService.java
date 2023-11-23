package com.mycom.enjoytrip.board.service;

import java.util.List;

import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;
import com.mycom.enjoytrip.board.dto.ReactionDto;

public interface BoardService {
	List<BoardDto>list(BoardParamDto dto);
	BoardDto detail(int bindex);
	int insert(BoardDto dto);
	int update(BoardDto dto);
	int delete(int bindex);
	
	//리액션
	ReactionDto reactionDetail(int boardId);
	int reactionUpdate(ReactionDto dto);
}
