package com.fse.taskmanager.service;

import java.text.ParseException;
import java.util.List;

import com.fse.taskmanager.model.Task;

public interface TaskManagerService {

	/**
	 * This method is used to fetch all the tasks
	 * 
	 * @return list of tasks
	 */
	List<Task> fetchAllTasks();

	/**
	 * This method is used to add / update a task
	 * 
	 * @param task
	 * @throws ParseException
	 */
	void saveTask(Task task) throws ParseException;

	/**
	 * This method is used to end a task
	 * 
	 * @param id
	 */
	void endTask(Integer id);

	/**
	 * This method is used to fetch specific task by id
	 * 
	 * @param id
	 * @return Task
	 */
	Task fetchTaskById(Integer id);
}
