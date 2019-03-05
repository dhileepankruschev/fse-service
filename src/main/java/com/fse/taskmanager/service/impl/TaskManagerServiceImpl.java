package com.fse.taskmanager.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fse.taskmanager.dao.TaskManagerDao;
import com.fse.taskmanager.model.ParentTask;
import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.service.TaskManagerService;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	TaskManagerDao taskManagerDao;

	/**
	 * This method is used to fetch all the tasks
	 * 
	 * @return list of tasks
	 */
	@Override
	public List<Task> fetchAllTasks() {

		List<Task> taskList = taskManagerDao.fetchAllTasks();

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		taskList.forEach(task -> {

			String stDate = simpleDateFormat.format(task.getStartDate());
			String endDate = simpleDateFormat.format(task.getEndDate());

			task.setStartDateStr(stDate);
			task.setEndDateStr(endDate);

			task.setParentTaskName("This task has no Parent");
			if (null != task.getParentTask()) {
				task.setParentTaskName(task.getParentTask().getParentTaskName());
				task.setParentId(task.getParentTask().getId());
			}
		});

		return taskList;
	}

	/**
	 * This method is used to add / update a task
	 * 
	 * @param task
	 * @throws ParseException
	 */
	@Override
	public void saveTask(Task task) throws ParseException {

		Date stDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getStartDateStr());
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getEndDateStr());

		task.setStartDate(stDate);
		task.setEndDate(endDate);

		if (null != task.getParentId()) {

			ParentTask parent = new ParentTask();

			Integer parentId = taskManagerDao.fetchParentTask(task.getParentTaskName());

			if (null != parentId) {

				parent.setId(parentId);

			} else {
				parent.setParentTaskName(task.getParentTaskName());
				parent = taskManagerDao.saveParentTask(parent);

			}

			task.setParentTask(parent);
		}

		taskManagerDao.saveTask(task);
	}

	/**
	 * This method is used to end a task
	 * 
	 * @param id
	 */
	@Override
	@Transactional
	public void endTask(Integer id) {

		taskManagerDao.endTask(id);
	}

	/**
	 * This method is used to fetch specific task by id
	 * 
	 * @param id
	 * @return Task
	 */
	@Override
	public Task fetchTaskById(Integer id) {

		Task task = taskManagerDao.fetchTaskById(id);

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String stDate = simpleDateFormat.format(task.getStartDate());
		String endDate = simpleDateFormat.format(task.getEndDate());

		task.setStartDateStr(stDate);
		task.setEndDateStr(endDate);

		task.setParentTaskName("This task has no Parent");
		if (null != task.getParentTask()) {
			task.setParentTaskName(task.getParentTask().getParentTaskName());
			task.setParentId(task.getParentTask().getId());
		}

		return task;
	}

}
