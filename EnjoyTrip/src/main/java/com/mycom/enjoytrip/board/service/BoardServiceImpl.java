package com.mycom.enjoytrip.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.board.dao.BoardDao;
import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;
import com.mycom.enjoytrip.board.dto.ReactionDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	@Override
	public List<BoardDto> list(BoardParamDto dto) {
		// TODO Auto-generated method stub
		return dao.list(dto);
	}

	@Override
	public BoardDto detail(int bindex) {
		// TODO Auto-generated method stub
		return dao.detail(bindex);
	}

	@Override
	public int insert(BoardDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(BoardDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int bindex) {
		// TODO Auto-generated method stub
		return dao.delete(bindex);
	}

	// 리액션
	@Override
	public ReactionDto reactionDetail(int boardId) {
		//없으면 만들어서 넣어주기
		if (dao.reactionDetail(boardId) == null) {
			dao.reactionInsert(new ReactionDto(boardId, 0, 0, 0, 0));
		}
		return dao.reactionDetail(boardId);
	}

	@Override
	public int reactionUpdate(ReactionDto dto) {

		return dao.reactionUpdate(dto);
	}

}
