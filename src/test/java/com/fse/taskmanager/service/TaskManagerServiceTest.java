package com.fse.taskmanager.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.repository.TaskRepository;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerServiceTest {

	@MockBean
	TaskRepository taskRepoMock;

	@Autowired
	TaskManagerService taskManagerService;

	@Test
	public void testFetchAllTasks() {
		List<Task> taskList = new ArrayList<>();
		Task task1 = new Task();
		task1.setTask("FirstTask");
		task1.setId(1);
		task1.setStartDate(new Date());
		task1.setEndDate(new Date());

		Task task2 = new Task();
		task2.setTask("SecondTask");
		task2.setId(2);
		task2.setStartDate(new Date());
		task2.setEndDate(new Date());

		taskList.add(task1);
		taskList.add(task2);

		when(taskRepoMock.findAll()).thenReturn(taskList);

		List<Task> taskListSvc = taskManagerService.fetchAllTasks();

		assertEquals(taskListSvc.size(), 2);
	}

	@Test
	public void testFetchTaskById() {

		Task task1 = new Task();
		task1.setTask("FirstTask");
		task1.setId(1);
		task1.setStartDate(new Date());
		task1.setEndDate(new Date());

		when(taskRepoMock.findById(1)).thenReturn(Optional.of(task1));

		Task taskListSvc = taskManagerService.fetchTaskById(1);

		assertEquals(taskListSvc.getTask(), "FirstTask");
	}

	@Test
	public void testSaveTask() throws ParseException {

		Task task1 = new Task();
		task1.setTask("FirstTask");
		task1.setStartDateStr("2019-05-03");
		task1.setEndDateStr("2019-05-03");

		when(taskRepoMock.save(task1)).thenReturn(task1);

		taskManagerService.saveTask(task1);
	}

	@Test
	public void testUpdateTask() throws ParseException {

		Task task1 = new Task();
		task1.setTask("FirstTask");
		task1.setStartDateStr("2019-05-03");
		task1.setEndDateStr("2019-05-03");
		task1.setId(1);

		when(taskRepoMock.save(task1)).thenReturn(task1);

		taskManagerService.saveTask(task1);
	}

	@Test
	public void testEndTask() throws ParseException {

		taskManagerService.endTask(1);
	}

}
