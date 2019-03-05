package com.fse.taskmanager.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.service.TaskManagerService;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskManagerController {

	@Autowired
	TaskManagerService taskManagerService;

	/**
	 * This method is used to fetch all the tasks
	 * 
	 * @return list of tasks
	 */
	@RequestMapping(value = "/fetchall", method = RequestMethod.GET)
	public List<Task> getAllTasks() {

		return taskManagerService.fetchAllTasks();
	}

	/**
	 * This method is used to add a task
	 * 
	 * @param task
	 * @throws ParseException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveTask(@RequestBody Task task) throws ParseException {

		taskManagerService.saveTask(task);
	}

	/**
	 * This method is used to update a task
	 * 
	 * @param task
	 * @throws ParseException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateTask(@RequestBody Task task) throws ParseException {

		taskManagerService.saveTask(task);
	}

	/**
	 * This method is used to end a task
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/endtask/{id}", method = RequestMethod.PUT)
	public void endTask(@PathVariable Integer id) {

		taskManagerService.endTask(id);
	}

	/**
	 * This method is used to fetch specific task by id
	 * 
	 * @param id
	 * @return Task
	 */
	@RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
	public Task fetchTaskById(@PathVariable Integer id) {

		return taskManagerService.fetchTaskById(id);
	}

}
