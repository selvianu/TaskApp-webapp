package com.cys.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.cys.mapper.TaskMapper;
import com.cys.mapper.UserMapper;
import com.cys.model.Task;
import com.cys.model.User;

@Repository
public class TaskDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void save(Task task) {
		String sql = "insert into tasks(task_name, created_date, status) values (?,?,?)";
		Date cdate = task.getCreatedDate();
		System.out.println(cdate);
		Object[] params = { task.getTaskName(), cdate, task.getStatus() };
		int insertTask = jdbcTemplate.update(sql, params);
		System.out.println(insertTask + "rows inserted..");
	}

	public int updateTaskName(Task task) {
		String updatesql = "update task set taskname=? where taskid=?";
		Object[] params = { task.getTaskName(), task.getTaskId() };
		int updateTaskName = jdbcTemplate.update(updatesql, params);
		System.out.println("No of task updated..." + updateTaskName);
		return updateTaskName;
	}

	public int updateTaskStatus(Task task) {
		String updatesql = "update task set status=? where taskid=?";
		Object[] params = { task.getStatus(), task.getTaskId() };
		int updateTaskStatus = jdbcTemplate.update(updatesql, params);
		System.out.println("No of task updated..." + updateTaskStatus);
		return updateTaskStatus;
	}

	public int deleteTask(Integer taskId) {
		String deleteSql = "delete from tasks where task_id=?";
		int recordToDelete = jdbcTemplate.update(deleteSql, taskId);
		return recordToDelete;
	}

	public List<Task> tasklist() {
		String tasksList = "select * from tasks";
		List<Task> tasks = jdbcTemplate.query(tasksList, new TaskMapper());
		for (Task task : tasks) {
			System.out.println(task);
		}
		return tasks;
	}

	@SuppressWarnings("rawtypes")
	public List listTaskByStatus(Task task) {
		String byStatus = "select * from tasks_todo where status= ?";
		Object[] params = { task.getStatus() };
		List<Task> taskByStatus = (List<Task>) jdbcTemplate.queryForObject(byStatus, new TaskMapper(), params);
		return taskByStatus;
	}

	public List<Task> taskForUser() {
		String query = "select * from tasks_todo where assigned_to=?";
		List<Task> taskListForUser = jdbcTemplate.query(query, new TaskMapper());
		return taskListForUser;
	}

}
