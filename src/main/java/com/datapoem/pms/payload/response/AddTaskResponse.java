package com.datapoem.pms.payload.response;

import java.io.Serializable;

import com.datapoem.pms.models.UserWithTaskDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTaskResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private UserWithTaskDto data;
}
