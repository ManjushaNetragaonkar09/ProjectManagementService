package com.datapoem.pms.security.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datapoem.pms.controllers.UserTaskController;
import com.datapoem.pms.models.TaskDto;
import com.datapoem.pms.models.UserWithTaskDto;
import com.datapoem.pms.repository.UserTaskRepository;
import com.datapoem.pms.vo.TaskVO;
import com.datapoem.pms.vo.UserTaskVO;

@Service
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired
	private UserTaskRepository userTaskRepository;

	private static final Logger log = LoggerFactory.getLogger(UserTaskController.class);

	public List<UserTaskVO> fetchUserTask(String userId) {
		log.info("Entering into UserTaskServiceImpl.fetchUserTask()");
		List<UserWithTaskDto> usersListDto = new ArrayList<>();
		List<UserTaskVO> usersListVO = new ArrayList<>();

		usersListDto = userTaskRepository.findByUserId(Integer.parseInt(userId));
		if (null != usersListDto && !usersListDto.isEmpty()) {
			usersListVO = usersListDto.stream().map(row -> {
				UserTaskVO vo = new UserTaskVO();
				Set<TaskDto> taskDtoList = new HashSet<>();
				Set<TaskVO> taskVOList = new HashSet<>();
				vo.setEmail(row.getEmail());
				vo.setUsername(row.getUsername());
				taskDtoList = row.getTasks();
				if(null==taskDtoList){
					vo.setTasks(new HashSet<>());
				}else if (!taskDtoList.isEmpty()) {
					taskVOList = taskDtoList.stream().map(row1 -> {
						return new TaskVO(row1.getName(), row1.getDeadline(), row1.getStatus());
					}).collect(Collectors.toSet());
				}
				vo.setTasks(taskVOList);
				return vo;
			}).collect(Collectors.toList());
		}

		log.info("Exiting from UserTaskServiceImpl.fetchUserTask()");
		return usersListVO;
	}
}
