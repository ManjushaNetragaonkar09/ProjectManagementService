package com.datapoem.pms.security.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datapoem.pms.vo.UserTaskVO;

@Service
public interface UserTaskService {

	public List<UserTaskVO> fetchUserTask(String userId);
}
