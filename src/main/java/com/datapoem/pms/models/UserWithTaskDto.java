package com.datapoem.pms.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity
@Table(name="users")
public class UserWithTaskDto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	 
	@OneToMany(targetEntity =TaskDto.class,cascade = CascadeType.ALL )
//	@JoinTable(name="task",
//	joinColumns = @JoinColumn(name="user_id"),
//	inverseJoinColumns = @JoinColumn(name="user_id_pk",insertable = false, updatable = false))
	@JoinColumn(name="user_id_pk",referencedColumnName = "user_id")
	private Set<TaskDto> tasks= new HashSet<>();
	
}











