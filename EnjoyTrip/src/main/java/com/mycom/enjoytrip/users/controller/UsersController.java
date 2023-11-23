package com.mycom.enjoytrip.users.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.users.dto.UsersDto;
import com.mycom.enjoytrip.users.service.UsersService;

@RestController
//@CrossOrigin(origins="*", allowedHeaders = "*")
public class UsersController {
    @Autowired
    private UsersService service;
    
    @GetMapping(value="/users")
    public List<UsersDto> list(){
        List<UsersDto>list = service.usersList();
        return list;
    }
    
    @GetMapping(value="/users/{userId}")
    public UsersDto detail(@PathVariable int userId){
        UsersDto dto = service.usersDetail(userId);
        return dto;
    }
    
    @PostMapping(value="/users")
    public int insert(@RequestBody UsersDto dto){
        return service.usersInsert(dto);
    }
    
    @PutMapping(value="/users/{userId}")
    public int update(@PathVariable int userId, @RequestBody UsersDto dto){
        System.out.println("updateUser!! ");
        System.out.println(dto);
        return service.usersUpdate(dto);
    }
    
    @DeleteMapping(value="/users/{userId}")
    public int delete(@PathVariable int userId){
        return service.usersDelete(userId);
    }
    
    @PostMapping(value="/users/login")
    public Map<String, String> login(@RequestBody UsersDto dto, HttpSession session){
        UsersDto userDto = service.login(dto);
        Map<String, String> map = new HashMap<>();
        System.out.println("==>" +userDto);
        
        //성공
        if(userDto != null) {
            session.setAttribute("userDto", userDto);//서버용도
            String id = userDto.getUserId()+"";
            //클라이언트
            map.put("result", "success");
            map.put("userName", userDto.getName());
            map.put("userEmail", userDto.getEmail());
            map.put("userId", id);
            map.put("userPhoneNum", userDto.getPhoneNum());
            map.put("userSelfIntro", userDto.getSelfIntro());
            map.put("userResidence", userDto.getResidence());
            map.put("userProfileImg", userDto.getProfileImg());
            //map.put("userProfileImageUrl", userDto.getUserProfileImageUrl());            
        }else {
            map.put("result", "fail");
        }
        return map;
    }
    
    // logout
    @GetMapping(value="/users/logout")
    public   Map<String, String>  logout(HttpSession session) {
        session.invalidate();
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
}