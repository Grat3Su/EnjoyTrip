package com.mycom.enjoytrip.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	List<BoardDto>list(BoardParamDto dto);
	
	BoardDto detail(int bindex);
	int insert(BoardDto dto);
	int update(BoardDto dto);
	int delete(int bindex);
}
