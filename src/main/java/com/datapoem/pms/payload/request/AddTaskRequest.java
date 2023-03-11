package com.datapoem.pms.payload.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTaskRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String taskName;
	private String deadLine;
	private String status;
	private String userId;
	private String description;
}
