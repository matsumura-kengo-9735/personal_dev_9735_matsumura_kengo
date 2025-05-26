package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("GETリクエストが正しく処理され、tasksテンプレートに返されること")
	void testIndex() throws Exception{
		mockMvc.perform(get("/tasks"))
		.andExpect(status().isOk())
		.andExpect(view().name("tasks"));
	}
	
	@Test
	@DisplayName("GEtリクエストが正しく処理され、addTaskテンプレートが返されること")
	void testTaskAddDisp() throws Exception{
		mockMvc.perform(get("/tasks/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("/addTask"));
	}
	
	@Test
	@DisplayName("タスク作成")
	void testTaskAdd() throws Exception{
		mockMvc.perform(post("/tasks/add"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/tasks"));
	}
}
