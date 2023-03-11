package com.datapoem.pms.payload.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ViewUserTaskRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	//userid,
	private String userId;
}
