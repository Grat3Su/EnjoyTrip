package com.mycom.enjoytrip.attr.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mycom.enjoytrip.attr.dto.AttrDto;
import com.mycom.enjoytrip.attr.dto.LikeDto;
import com.mycom.enjoytrip.attr.dto.LocDto;

public interface AttrService {
	List<AttrDto>list(int pageIdx);

	List<LocDto> sido();
	List<LocDto> gugun(int sidoCode);

	List<AttrDto>sidoList(int sidoCode, int pageIdx);
	List<AttrDto>gugunList(int sidoCode,int gugunCode, int pageIdx);
	AttrDto detail(int contentId);
	int insert(AttrDto dto);
	int update(AttrDto dto);
	int delete(int contentId);
	
//	Like문
	List<Integer> likeList(@Param("userId") int userId);
	List<LikeDto> likeDetailList(@Param("userId") int userId);
	int likeInsert(@Param("userId") int userId,@Param("contentId") int contentId);
	int likeDelete(@Param("userId") int userId,@Param("contentId") int contentId);
}
