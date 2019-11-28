package com.bringoz.driversmanagement.controller;

import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bringoz.driversmanagement.exception.DriverManagmentExceptions;
import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;
import com.bringoz.driversmanagement.service.DriverServiceImpl;

@RestController
@RequestMapping("/driver-service")
public class DriverController {
	
	@Autowired
	private DriverServiceImpl driverService;
	
	
	@RequestMapping(value = "createDriver", method=RequestMethod.POST)
	public ResponseEntity<String> createDriver(@RequestBody Driver driver) throws DriverManagmentExceptions {
		driverService.create(driver);
		return ResponseEntity.ok().body("New Driver has been saved");
	}

	@RequestMapping(value = "getDriverById", method=RequestMethod.GET)
	public Driver getDriverById(@RequestParam Long id) throws DriverManagmentExceptions {
		return driverService.findById(id);
	}
	
	@RequestMapping(value = "getAllDrivers", method=RequestMethod.GET)
	public List<Driver> getAllDrivers() {
		return driverService.findAllDrivers();
	}
	
	@RequestMapping(value = "getAllActiveDrivers", method=RequestMethod.GET)
	public List<Driver> getAllActiveDrivers() {
		return driverService.findAllActive();
	}
	
	@RequestMapping(value = "getAllDeliveringDrivers", method=RequestMethod.GET)
	public List<Driver> getAllDeliveringDrivers() {
		return driverService.findAllDelivering();
	}
	
	@RequestMapping(value = "getAllInactiveDrivers", method=RequestMethod.GET)
	public List<Driver> getAllInactiveDrivers() {
		return driverService.findAllInActive();
	}
	
	@RequestMapping(value = "updateDriver", method = RequestMethod.POST)
	public ResponseEntity<String> updateDriver(@RequestBody Driver driver) throws DriverManagmentExceptions{
		driverService.update(driver);
		return ResponseEntity.ok().body("Driver with id: " + driver.getId() + " successfully updated ");
	}
	
	@RequestMapping(value = "removeDriver/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> removeDriverById(@PathVariable("id") long id){
		driverService.remove(id);
		return ResponseEntity.ok().body("Driver with id: " + id + " has been removed");
	}
	
	@RequestMapping(value = "changeStatusToActive/{id}", method=RequestMethod.POST)
	public ResponseEntity<String> changeStatusToActive(@PathVariable("id") long id) throws DriverManagmentExceptions {
		 driverService.changeStatusToActive(id);
		 return	ResponseEntity.ok().body("Driver status changed to ACTIVE");
	}
	
	@RequestMapping(value = "changeStatusToInactive/{id}", method=RequestMethod.POST)
	public ResponseEntity<String> changeStatusToInactive(@PathVariable("id") long id) throws DriverManagmentExceptions {
		driverService.changeStatusToInactive(id);
		return	ResponseEntity.ok().body("Driver status changed to INACTIVE");
	}
	
	@RequestMapping(value = "changeStatusToDelivering/{id}", method=RequestMethod.POST)
	public ResponseEntity<String> changeStatusToDelivering(@PathVariable("id") long id) throws DriverManagmentExceptions {
		driverService.changeStatusToDelivering(id);
		return	ResponseEntity.ok().body("Driver status changed to DELIVERING");
	}
	
	@RequestMapping(value = "availableDriver", method = RequestMethod.POST)
	public List<Driver> getAllAvailableDriversByTime(@RequestParam("startTime")
									   @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime, 
									   @RequestParam("endTime")
									   @DateTimeFormat(pattern = "HH:mm:ss") LocalTime endTime ) {
		return driverService.findAllAvailableByTime(startTime, endTime);
	}
	
	
}
