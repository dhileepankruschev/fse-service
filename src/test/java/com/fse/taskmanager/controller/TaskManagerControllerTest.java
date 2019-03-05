package com.fse.taskmanager.controller;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.service.TaskManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerControllerTest {

	@MockBean
	TaskManagerService taskManagerServiceMock;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webAppCtx;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webAppCtx);
		this.mockMvc = builder.build();
	}

	@Test
	public void testGetAllTasks() throws ParseException {

		try {
			this.mockMvc.perform(get("/task/fetchall").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTaskById() throws ParseException {

		try {
			this.mockMvc.perform(get("/task/fetch/" + 1).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveTask() throws ParseException {

		Task task = new Task();
		task.setTask("Junit Task");

		try {
			this.mockMvc.perform(post("/task/save").contentType(MediaType.APPLICATION_JSON).content(toJson(task)))
					.andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTask() throws ParseException {

		Task task = new Task();
		task.setTask("Junit Task Update");
		task.setId(1);

		try {
			this.mockMvc.perform(put("/task/update").contentType(MediaType.APPLICATION_JSON).content(toJson(task)))
					.andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteTask() throws ParseException {

		Task task = new Task();
		task.setTask("Junit Task");
		task.setId(2);
		task.setDeleteFlag("Y");

		try {
			this.mockMvc
					.perform(put("/task/endtask/" + 2).contentType(MediaType.APPLICATION_JSON).content(toJson(task)))
					.andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] toJson(Object obj) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsBytes(obj);
	}

}
