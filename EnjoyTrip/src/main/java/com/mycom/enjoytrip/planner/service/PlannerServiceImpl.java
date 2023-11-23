package com.mycom.enjoytrip.planner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.planner.dao.PlannerDao;
import com.mycom.enjoytrip.planner.dto.PlanListDto;
import com.mycom.enjoytrip.planner.dto.PlannerDto;

@Service
public class PlannerServiceImpl implements PlannerService{

	@Autowired
	private PlannerDao dao;
	
	@Override
	public List<PlanListDto> list(int userId) {
		return dao.list(userId);
	}

	@Override
	public PlannerDto detail(int userId, int day) {
		return dao.detail(userId, day);
	}

	@Override
	public int insert(PlannerDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(PlannerDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int userId) {
		return dao.delete(userId);
	}

}
