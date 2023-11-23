package com.mycom.enjoytrip.planner.service;

import java.util.List;

import com.mycom.enjoytrip.planner.dto.PlanListDto;
import com.mycom.enjoytrip.planner.dto.PlannerDto;

public interface PlannerService {
	List<PlanListDto> list(int userId);
	
	PlannerDto detail(int userId, int day);
	int insert(PlannerDto dto);
	int update(PlannerDto dto);
	int delete(int userId);
	
}
