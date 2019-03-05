package com.fse.taskmanager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fse.taskmanager.dao.TaskManagerDao;
import com.fse.taskmanager.model.ParentTask;
import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.repository.ParentTaskRepository;
import com.fse.taskmanager.repository.TaskRepository;

@Repository
public class TaskManagerDaoImpl implements TaskManagerDao {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ParentTaskRepository parentTaskRepository;

	/**
	 * This method is used to fetch all the tasks
	 * 
	 * @return list of tasks
	 */
	@Override
	public List<Task> fetchAllTasks() {

		return taskRepository.findAll();
	}

	/**
	 * This method is used to add / update a task
	 * 
	 * @param task
	 */
	@Override
	public void saveTask(Task task) {

		taskRepository.save(task);
	}

	/**
	 * This method is used to fetch the parent task
	 * 
	 * @param task
	 * @return Parent task id
	 */
	@Override
	public Integer fetchParentTask(String taskName) {

		return taskRepository.findParentTask(taskName);
	}

	/**
	 * This method is used to save the parent task
	 * 
	 * @param parentTask
	 * @return ParentTask
	 */
	@Override
	public ParentTask saveParentTask(ParentTask parentTask) {

		return parentTaskRepository.save(parentTask);
	}

	/**
	 * This method is used to end a task
	 * 
	 * @param id
	 */
	@Override
	public void endTask(Integer id) {

		taskRepository.endTask(id);
	}

	/**
	 * This method is used to fetch specific task by id
	 * 
	 * @param id
	 * @return Task
	 */
	@Override
	public Task fetchTaskById(Integer id) {

		return taskRepository.findById(id).get();
	}

}
