package com.bringoz.driversmanagement;


import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;
import com.bringoz.driversmanagement.service.DriverServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(DriverServiceImpl.class)
class DriverServiceTest {

	@MockBean
	private DriverServiceImpl driverService;
	
	Driver driver = new Driver(
			1L,"Mockito1","Driver",77,"Homeless",DriverStatus.ACTIVE,LocalTime.of(8, 10),LocalTime.of(17, 30),true);
	
	@Test
	public void createDriver() {
		
		Mockito.doNothing().when(driverService).create(driver);
		driverService.create(driver);
		
		Mockito.verify(driverService, Mockito.times(1)).create(driver);
	}
	
	@Test
	public void getDriverById() {
		Mockito.when(driverService.findById(driver.getId())).thenReturn(driver);
		
	}

}
