package com.datapoem.pms.security.services;

import java.text.ParseException;

import com.datapoem.pms.models.UserWithTaskDto;
import com.datapoem.pms.payload.request.AddTaskRequest;

public interface AddNewTaskService {

	public UserWithTaskDto addNewTaskToUser(AddTaskRequest req) throws ParseException ;
}
