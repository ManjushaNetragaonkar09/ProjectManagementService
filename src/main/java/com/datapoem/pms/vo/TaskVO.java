package com.datapoem.pms.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String taskName;
	private Date deadLine;
	private String status;
}
