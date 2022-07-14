package com.cys.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cys.dao.TaskDao;
import com.cys.model.Task;

public class TaskController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	TaskDao taskDao;

	@RequestMapping("/")
	public String home() {
		return "addTask.jsp";
	}

	@GetMapping("/inserttask")
	public String saveTask(@RequestParam("taskName") String taskName) {
		// log.info(" insert {}", taskName);
		System.out.println("saving tas...");
		Task t = new Task();
//		t.taskId = taskId;
		t.setTaskName(taskName);
		t.setStatus("Pending");
		String sDate1 = "12/07/2022";
		java.util.Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t.setCreatedDate((Date) date1);
		System.out.println("Task Name : " + t.getTaskName() + "status: " + t.getStatus() + t.getCreatedDate());
		taskDao.save(t);
		return "success2.jsp";
	}

	@GetMapping("/updatetask")
	public String updateTaskName(@RequestParam("taskId") Integer taskId, @RequestParam("taskName") String taskName) {
		Task t = new Task();
		t.setTaskId(taskId);
		t.setTaskName(taskName);
		taskDao.updateTaskName(t);
		System.out.println("Task name update by id");
		return "listoftask.jsp";
	}

	@GetMapping("/updatetaskstatus")
	public String updateTaskStatus(@RequestParam("taskId") Integer taskId, @RequestParam("status") String status) {
		Task t = new Task();
		t.setTaskId(taskId);
		t.setStatus(status);
		taskDao.updateTaskStatus(t);
		System.out.println("Task name update by id");
		return "listoftask.jsp";

	}

	@GetMapping("/deletetask")
	public String deleteTask(@RequestParam("taskId") Integer taskId) {
		int deleteRecord = taskDao.deleteTask(taskId);
		System.out.println("No of Task Deleted : " + deleteRecord);
		return "listoftask.jsp";
	}

	@GetMapping("/listoftask")
	public String taskList(Model model) {
		List<Task> tasklist = taskDao.tasklist();
		System.out.println(tasklist);
		// to display these values in jsp, we are adding to model and using key the same
		// can be displayed in jsp
		model.addAttribute("task_list", tasklist);
		return "listoftask.jsp";
	}

	@GetMapping("/taskByStatus")
	public String taskListByStatus(Model model) {
		List<Task> listTaskByStatus = taskDao.listTaskByStatus();
		model.addAttribute("BYSTATUS", listTaskByStatus);
		return "listoftaskbystatus.jsp";
	}

	@GetMapping("taskforuser")
	public String taskListForUser(Model model) {
		List<Task> listTaskForUser = taskDao.taskForUser();
		model.addAttribute("BYSTATUS", listTaskForUser);
		return "tasklistuser.jsp";

	}
}
