package com.unit.testing.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import com.unit.testing.db.UserDao;
import com.unit.testing.models.User;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock private static HttpServletRequest servletRequest;
	@Mock private static HttpSession session;
	@Mock private UserDao userDao;
	
	private static User user;
	private static Map<String, Object> results;
	private static UserService userService;


	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		user = new User();
		user.setUsername("ana");
		user.setId(5);
		
		userService = spy(new UserService());
		results = new HashMap<>();
		results.put("status", "OK");
		results.put("user", user);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		when(servletRequest.getSession()).thenReturn(session);
		when(session.getAttribute("auth")).thenReturn(true);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testgetUserById_givenUserId_returnsStatus200() {
		lenient().when(userDao.getUserById(anyInt())).thenReturn(results);
		when(userService.getUserDao()).thenReturn(userDao);
		Response response = userService.getUserById(servletRequest, 2);
//		User returnedUser = (User) response.getEntity();
		assertEquals(200, response.getStatus());
		verify(userDao, times(1)).getUserById(2);
		
	}
	


}
