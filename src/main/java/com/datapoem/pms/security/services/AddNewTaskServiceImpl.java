package com.datapoem.pms.security.services;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapoem.pms.models.TaskDto;
import com.datapoem.pms.models.UserWithTaskDto;
import com.datapoem.pms.payload.request.AddTaskRequest;
import com.datapoem.pms.repository.UserTaskRepository;

@Service
public class AddNewTaskServiceImpl implements AddNewTaskService {

	private static final Logger log = LoggerFactory.getLogger(AddNewTaskServiceImpl.class);

	@Autowired
	private UserTaskRepository userTaskRepository;

	public UserWithTaskDto addNewTaskToUser(AddTaskRequest req) throws ParseException {
		log.info("Enterint into AddNewTaskServiceImpl.addNewTaskToUser()");

		UserWithTaskDto userTaskDto = new UserWithTaskDto();
		TaskDto dto = new TaskDto();
		Set<TaskDto> taskDtoSet = new HashSet<>();

		dto.setDeadline(com.datapoem.pms.util.DateConverter.stringToSqlDate(req.getDeadLine()));
		dto.setDescription(req.getDescription());
		dto.setName(req.getTaskName());
		dto.setStatus(req.getStatus());
		dto.setUserId(Integer.parseInt(req.getUserId()));
		taskDtoSet.add(dto);
		userTaskDto.setTasks(taskDtoSet);
		userTaskDto = userTaskRepository.save(userTaskDto);
		log.info("Exiting from AddNewTaskServiceImpl.addNewTaskToUser()");
		return userTaskDto;
	};
}
