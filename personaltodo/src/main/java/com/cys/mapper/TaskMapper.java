package com.cys.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cys.model.Task;
import com.cys.model.User;

public class TaskMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
		// setting above values to display(in task-object)

		// getting the values to map DB

		int task_id = rs.getInt("TASK_ID");
		String task_name = rs.getString("TASK_NAME");
		Date taskCreatedDate = rs.getDate("CREATED_DATE");
		String taskStatus = rs.getString("status");
		// setting above values to display(in user-object)

		task.setTaskId(task_id);
		task.setTaskName(task_name);
		task.setCreatedDate(taskCreatedDate);
		task.setStatus(taskStatus);
		System.out.println(task.getTaskName() + task.getStatus());
		return task;
	}

}
