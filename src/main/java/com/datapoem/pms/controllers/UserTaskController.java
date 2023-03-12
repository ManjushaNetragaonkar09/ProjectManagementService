package com.datapoem.pms.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datapoem.pms.exception.UserTaskException;
import com.datapoem.pms.models.UserWithTaskDto;
import com.datapoem.pms.payload.request.AddTaskRequest;
import com.datapoem.pms.payload.response.AddTaskResponse;
import com.datapoem.pms.payload.response.ViewUserTaskResponse;
import com.datapoem.pms.security.services.AddNewTaskService;
import com.datapoem.pms.security.services.UserTaskService;
import com.datapoem.pms.vo.UserTaskVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/user/")
public class UserTaskController {

	private static final Logger log = LoggerFactory.getLogger(UserTaskController.class);

	@Autowired
	private UserTaskService userTaskService;

	@Autowired
	private AddNewTaskService addNewTaskService;

	//view user tasks ,needs userId
	@GetMapping("{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<ViewUserTaskResponse> fetchUserTasks(@PathVariable(name = "userId") String userId) {

		log.info("Entering into UserTaskController.fetchUserTasks()");
		log.info("userId from ui:-" + userId);

		ViewUserTaskResponse response = new ViewUserTaskResponse();
		List<UserTaskVO> userWithTasks = new ArrayList<>();
		try {

			if (StringUtils.isNotEmpty(userId)) {
				userWithTasks = userTaskService.fetchUserTask(userId);

				if (!userWithTasks.isEmpty()) {
					response.setData(userWithTasks);
					response.setMessage("SUCCESS");
				}

				log.info("Exiting from UserTaskController.fetchUserTasks()");
				return new ResponseEntity<ViewUserTaskResponse>(response, HttpStatus.OK);
			} else {
				response.setData(userWithTasks);
				response.setMessage("UserId should not be empty");
				log.info("Exiting from UserTaskController.fetchUserTasks()");

				return new ResponseEntity<ViewUserTaskResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occured while fetching userTasks");
			throw new UserTaskException("Error occured while fetching userTasks", e);
		}

	}

	//insert new task, input: taskName,deadline,status, userId
	//insert new task to other

	@PostMapping("/{userId}")
	public ResponseEntity<AddTaskResponse> addTask(@PathVariable(name = "userId") String userId,
			@RequestBody AddTaskRequest req) {
		log.info("Entering into UserTaskController.addTask()");
		AddTaskResponse resp = new AddTaskResponse();
		UserWithTaskDto taskAdded = new UserWithTaskDto();
		try {
			if (null != req) {
				taskAdded = addNewTaskService.addNewTaskToUser(req);
				resp.setMessage("SUCCESS");
				resp.setData(taskAdded);
				return new ResponseEntity<AddTaskResponse>(resp, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			log.error("Error occurred while saving new task to a user");
			throw new UserTaskException("Error occurred while saving new task to a user", e);
		}
		log.info("Exiting from UserTaskController.addTask()");
		return null;
	}

	// update task //task details,userId
	@PostMapping("/{userId}/{taskId}")
	public ResponseEntity<AddTaskResponse> addTask(@PathVariable(name = "userId") String userId,
			@RequestBody AddTaskRequest req,@PathVariable(name="taskId") String taskId) {
		log.info("Entering into UserTaskController.addTask()");
		AddTaskResponse resp = new AddTaskResponse();
		UserWithTaskDto taskAdded = new UserWithTaskDto();
		try {
			if (null != req) {
				taskAdded = addNewTaskService.updateNewTaskToUser(req);
				resp.setMessage("SUCCESS");
				resp.setData(taskAdded);
				return new ResponseEntity<AddTaskResponse>(resp, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Error occurred while saving new task to a user");
			throw new UserTaskException("Error occurred while saving new task to a user", e);
		}
		log.info("Exiting from UserTaskController.addTask()");
		return null;
	}
	
}
