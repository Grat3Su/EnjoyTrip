package com.mycom.enjoytrip.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanListDto {

	int userId, contentId, day;
	String addr1, title, firstImage;
}
