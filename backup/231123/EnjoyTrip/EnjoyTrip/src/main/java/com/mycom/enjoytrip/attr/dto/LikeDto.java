package com.mycom.enjoytrip.attr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
	int contentId;
	String title, firstImage, addr1,addr2,overview;
}
