package com.datapoem.pms.payload.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.datapoem.pms.vo.UserTaskVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewUserTaskResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	List<UserTaskVO> data = new ArrayList<>();
	String message;
	
}
