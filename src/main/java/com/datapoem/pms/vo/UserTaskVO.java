package com.datapoem.pms.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class UserTaskVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private Set<TaskVO> tasks;
	
}
