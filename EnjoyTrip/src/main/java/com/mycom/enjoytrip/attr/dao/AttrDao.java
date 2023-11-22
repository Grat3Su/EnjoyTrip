package com.mycom.enjoytrip.attr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.enjoytrip.attr.dto.AttrDto;
import com.mycom.enjoytrip.attr.dto.LikeDto;
import com.mycom.enjoytrip.attr.dto.LocDto;

@Mapper
public interface AttrDao {
	List<AttrDto>list(int pageIdx);
	List<AttrDto>sidoList(@Param("sidoCode") int sidoCode,@Param("pageIdx") int pageIdx);
	List<AttrDto>gugunList(@Param("sidoCode")int sidoCode, @Param("gugunCode")int gugunCode, @Param("pageIdx")int pageIdx);
	List<LocDto> sido();
	List<LocDto> gugun(int sidoCode);
	AttrDto detail(int contentId);
	int insert(AttrDto dto);
	int insert2(AttrDto dto);
	int update(AttrDto dto);
	int update2(AttrDto dto);
	int delete(int contentId);
	int delete2(int contentId);
	
	//id를 받아와서 content id를 리스트로 받아옴
	//로그인 한 사람걸 받아오기 때문에
	List<Integer> likeList(@Param("userId") int userId);
	List<LikeDto> likeDetailList(@Param("userId") int userId);
	int likeInsert(@Param("userId") int userId,@Param("contentId") int contentId);
	int likeDelete(@Param("userId") int userId,@Param("contentId") int contentId);
}
