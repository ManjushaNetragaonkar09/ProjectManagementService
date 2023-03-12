package com.datapoem.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datapoem.pms.models.UserWithTaskDto;

@Repository
public interface UserTaskRepository extends JpaRepository<UserWithTaskDto, Integer>{

	List<UserWithTaskDto> findByUserId(int userId);
 
}
