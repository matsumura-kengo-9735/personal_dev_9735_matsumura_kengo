package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Test
	@DisplayName("ログイン成功")
	void testLoginSuccess() throws Exception{
		mockMvc.perform(post("/login")
		.param("email","tanaka@aaa.com")
		.param("password","test123"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/tasks"));
	}
	
	@Test
	@DisplayName("email間違い")
	void testLoginWithoutEmail() throws Exception{
		String email = null;
		String password = null;
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		mockMvc.perform(post("/login")
				.param("email","sato@aaa.com")
				.param("password","himitu"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attribute("message","メールアドレスとパスワードが一致しませんでした"));
	}
	@Test
	@DisplayName("password間違い")
	void testLoginWithoutPassword() throws Exception{
		String email = null;
		String password = null;
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		mockMvc.perform(post("/login")
				.param("email","tanaka@aaa.com")
				.param("password","tigau"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attribute("message","メールアドレスとパスワードが一致しませんでした"));
	}
	@Test
	@DisplayName("emailとpassword間違い")
	void testLoginWithoutEmailandpassword() throws Exception{
		String email = null;
		String password = null;
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		mockMvc.perform(post("/login")
				.param("email","sato@aaa.com")
				.param("password","tigau"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attribute("message","メールアドレスとパスワードが一致しませんでした"));
	}
}
