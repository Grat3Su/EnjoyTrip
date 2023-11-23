package com.mycom.enjoytrip.attr.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.attr.dto.AttrDto;
import com.mycom.enjoytrip.attr.dto.LikeDto;
import com.mycom.enjoytrip.attr.dto.LocDto;
import com.mycom.enjoytrip.attr.service.AttrService;

@RestController
//@CrossOrigin(origins="*", allowedHeaders = "*")
public class AttrController {
	@Autowired
	private AttrService service;
	
	@GetMapping(value="attrs/list/{pageIdx}")
	public List<AttrDto> list(@PathVariable int pageIdx){
		List<AttrDto> list = service.list(pageIdx);
		System.out.println(list);
		return list;
	}
	
	@GetMapping(value="attrs/searchlist")
	public List<AttrDto> searchList(@RequestParam("searchWord") String searchWord, @RequestParam("pageIdx") int pageIdx){
		List<AttrDto> list = service.searchList(searchWord, pageIdx);
		System.out.println(list);
		return list;
	}
	
	@GetMapping(value="attrs/sido")
	public List<LocDto> sido(){
		List<LocDto> list = service.sido();
		return list;
	}
	
	@GetMapping(value="attrs/gugun/{sidoCode}")
	public List<LocDto> gugun(@PathVariable int sidoCode){
		List<LocDto> list = service.gugun(sidoCode);
		return list;
	}
	
	@GetMapping(value="attrs/sidolist")
	public List<AttrDto> sidoList(@RequestParam("pageIdx") int pageIdx, @RequestParam("sidoCode") int sidoCode){
		//System.out.println(sidoCode);
		List<AttrDto> list = service.sidoList(sidoCode, pageIdx);
		return list;
	}
	
	@GetMapping(value="attrs/gugunlist")
	public List<AttrDto> gugunList(@RequestParam("pageIdx") int pageIdx, @RequestParam("sidoCode") int sidoCode,@RequestParam("gugunCode") int gugunCode){
		List<AttrDto> list = service.gugunList(sidoCode, gugunCode, pageIdx);
		return list;
	}
	
	@GetMapping(value="/attrs/{contentId}")
	public AttrDto detail(@PathVariable int contentId){
		AttrDto dto = service.detail(contentId);
		return dto;
	}
	
	@PostMapping(value="/attrs")
	public int insert(AttrDto dto){
		return service.insert(dto);
	}
	
	@PutMapping(value="/attrs/{contentId}")
	public int update(AttrDto dto){
		return service.update(dto);
	}
	
	@DeleteMapping(value="/attrs/{contentId}")
	public int delete(@PathVariable int contentId){
		return service.delete(contentId);
	}
	
//	likes	
	@GetMapping(value="/likes/{userId}")
	public List<Integer> likeList(@PathVariable int userId){
		return service.likeList(userId);
	}
	
	@GetMapping(value="/likes/detail/{userId}")
	public List<LikeDto> likeDetailList(@PathVariable int userId){
		return service.likeDetailList(userId);
	}
	
	@PostMapping(value="/likes")
	public int likeInsert(@RequestParam("userId") int userId, @RequestParam("contentId") int contentId){
		System.out.println("insertLike!!!");
		return service.likeInsert(userId, contentId);
	}
	
	@DeleteMapping(value="/likes")
	public int likeDelete(@RequestParam("userId") int userId, @RequestParam("contentId") int contentId){
		System.out.println("deletelike!");
		return service.likeDelete(userId, contentId);
	}
}
