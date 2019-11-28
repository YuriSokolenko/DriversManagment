package com.bringoz.driversmanagement;


import java.time.LocalTime;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import com.bringoz.driversmanagement.controller.DriverController;
import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;
import com.bringoz.driversmanagement.service.DriverServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(DriverController.class)
public class DriverControllerTests {
	
	@MockBean
	private DriverServiceImpl driverService;
	
	@Autowired
    private MockMvc mvc;
	
	Driver driver = new Driver(
			1L,"Mockito1","Driver",77,"Homeless",DriverStatus.ACTIVE,LocalTime.of(8, 10),LocalTime.of(17, 30),true);
	
	String exampleDriverJson = "{\"id\":\"1\",\"firstName\":\"David\",\"lastName\":\"Rishon\",\"age\":\"29\",\"address\":\"Jerusalem, Alenby st. 46\",\"status\":\"ACTIVE\",\"start\":\"08:15\",\"end\":\"22:15\",\"isInMapBounds\":\"false\"}";

	@Test
	public void creatingDriver_expectingReturnsString() throws Exception{
		
		String result = mvc.perform(post("http://localhost:8081/driver-service/createDriver").
				content(exampleDriverJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals("New Driver has been saved", result);
	}
	
	@Test
	public void getDriverById() throws Exception {
		
		mvc.perform( MockMvcRequestBuilders
			      .get("http://localhost:8081/driver-service/getDriverById/{id}", 1111)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers.status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	
}
