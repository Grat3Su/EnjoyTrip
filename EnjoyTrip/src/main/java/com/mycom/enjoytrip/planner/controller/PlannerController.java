package com.mycom.enjoytrip.planner.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.planner.dto.PlanListDto;
import com.mycom.enjoytrip.planner.dto.PlannerDto;
import com.mycom.enjoytrip.planner.service.PlannerService;

@RestController
public class PlannerController {
    @Autowired
    private PlannerService service;
    
    @GetMapping(value="/planners/{userId}")
    public List<PlanListDto> list(@PathVariable int userId){
        List<PlanListDto> list = service.list(userId);        
        System.out.println("====>"+list.size());
        System.out.println("====>"+list);
        return list;
    }
    
//    @GetMapping(value="/planners/{userId}/{day}")
//    public PlannerDto detail(@PathVariable int userId, @PathVariable int day){
//    	PlannerDto dto = service.detail(userId,day);
//        return dto;
//    }
    
    
    @PostMapping(value="/planners")
    public int insert(@RequestBody PlannerDto dto){
    	System.out.println("getContentId: "+dto.getContentId());
    	System.out.println("getUserId: "+dto.getUserId());
    	System.out.println("getDay: "+dto.getDay());
        return service.insert(dto);
    }
    
    @PutMapping(value="/planners/{userId}/{day}")
    public int update(@PathVariable int userId, @PathVariable int day,@RequestBody PlannerDto dto){
        System.out.println("update");
//        dto.setUserId(userId);
        return service.update(dto);
    }
    
    @DeleteMapping(value="/planners/{userId}")
    public int delete(@PathVariable int userId){
    	Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        System.out.println("delete!!");
        return service.delete(userId);
    }
    
}