package com.mycom.enjoytrip.planner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.planner.dto.PlanListDto;
import com.mycom.enjoytrip.planner.dto.PlannerDto;


@Mapper	
public interface PlannerDao {
	List<PlanListDto> list(int userId);
	
	
	PlannerDto detail(int userId, int day);
	int insert(PlannerDto dto);
	int update(PlannerDto dto);
	int delete(int userId);
	
}
