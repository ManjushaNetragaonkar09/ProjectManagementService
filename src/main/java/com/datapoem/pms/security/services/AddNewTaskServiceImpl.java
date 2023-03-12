package com.datapoem.pms.security.services;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapoem.pms.models.TaskDto;
import com.datapoem.pms.models.UserWithTaskDto;
import com.datapoem.pms.payload.request.AddTaskRequest;
import com.datapoem.pms.repository.UserTaskRepository;
import com.datapoem.pms.util.DateConverter;

@Service
public class AddNewTaskServiceImpl implements AddNewTaskService {

	private static final Logger log = LoggerFactory.getLogger(AddNewTaskServiceImpl.class);

	@Autowired
	private UserTaskRepository userTaskRepository;

	public UserWithTaskDto addNewTaskToUser(AddTaskRequest req) throws ParseException {
		log.info("Enterint into AddNewTaskServiceImpl.addNewTaskToUser()");

		UserWithTaskDto userTaskDto = new UserWithTaskDto();
		Set<TaskDto> taskDtoSet = new HashSet<>();

		userTaskDto = userTaskRepository.findByUserId(Integer.parseInt(req.getUserId())).get(0);
		taskDtoSet = userTaskDto.getTasks();

		taskDtoSet = taskDtoSet.stream().map(row -> {
			if (row.getTaskId() == row.getUserId()) {
				try {
					row.setDeadline(DateConverter.stringToSqlDate(req.getDeadLine()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				row.setDescription(req.getDescription());
				row.setName(req.getTaskName());
				row.setStatus(req.getStatus());
			}
			return row;
		}).collect(Collectors.toSet());
		

		userTaskDto.getTasks().addAll(taskDtoSet);

		userTaskDto = userTaskRepository.save(userTaskDto);

		log.info("Exiting from AddNewTaskServiceImpl.addNewTaskToUser()");
		return userTaskDto;
	}

//	public UserWithTaskDto updateNewTaskToUser(AddTaskRequest req) throws ParseException {
//		log.info("Enterint into AddNewTaskServiceImpl.addNewTaskToUser()");
//
//		UserWithTaskDto userTaskDto = new UserWithTaskDto();
//		TaskDto dto = new TaskDto();
//		Set<TaskDto> taskDtoSet = new HashSet<>();
//
//		userTaskDto = userTaskRepository.findByUserId(Integer.parseInt(req.getUserId())).get(0);
//
//		dto.setDeadline(com.datapoem.pms.util.DateConverter.stringToSqlDate(req.getDeadLine()));
//		dto.setDescription(req.getDescription());
//		dto.setName(req.getTaskName());
//		dto.setStatus(req.getStatus());
//		dto.setUserId(Integer.parseInt(req.getUserId()));
//		taskDtoSet.add(dto);
//		userTaskDto.getTasks().addAll(taskDtoSet);
//
//		userTaskDto = userTaskRepository.save(userTaskDto);
//		log.info("Exiting from AddNewTaskServiceImpl.addNewTaskToUser()");
//		return userTaskDto;
//	}
	
	public UserWithTaskDto updateNewTaskToUser(AddTaskRequest req) throws ParseException {
		log.info("Enterint into AddNewTaskServiceImpl.addNewTaskToUser()");

		UserWithTaskDto userTaskDto = new UserWithTaskDto();
		Set<TaskDto> taskDtoSet = new HashSet<>();

		userTaskDto = userTaskRepository.findByUserId(Integer.parseInt(req.getUserId())).get(0);
		taskDtoSet = userTaskDto.getTasks();

		taskDtoSet = taskDtoSet.stream().map(row -> {
			if (row.getTaskId() == row.getUserId()) {
				try {
					row.setDeadline(DateConverter.stringToSqlDate(req.getDeadLine()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				row.setDescription(req.getDescription());
				row.setName(req.getTaskName());
				row.setStatus(req.getStatus());
			}
			return row;
		}).collect(Collectors.toSet());
		

		userTaskDto.getTasks().addAll(taskDtoSet);

		userTaskDto = userTaskRepository.save(userTaskDto);

		log.info("Exiting from AddNewTaskServiceImpl.addNewTaskToUser()");
		return userTaskDto;
	}
}
