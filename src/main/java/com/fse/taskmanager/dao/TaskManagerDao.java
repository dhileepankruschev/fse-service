package com.fse.taskmanager.dao;

import java.text.ParseException;
import java.util.List;

import com.fse.taskmanager.model.ParentTask;
import com.fse.taskmanager.model.Task;

public interface TaskManagerDao {

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
	void saveTask(Task task);

	/**
	 * This method is used to fetch tha parent task
	 * 
	 * @param task
	 * @throws ParseException
	 * @return Parent task id
	 */
	Integer fetchParentTask(String taskName);

	/**
	 * This method is used to save the parent task
	 * 
	 * @param parentTask
	 * @return ParentTask
	 */
	ParentTask saveParentTask(ParentTask parentTask);

	/**
	 * This method is used to end a task
	 * 
	 * @param id
	 * @throws ParseException
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
