package com.bringoz.driversmanagement.service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import com.bringoz.driversmanagement.exception.DriverManagmentExceptions;
import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;

public interface IDriverService {

	public void create (Driver driver) throws DriverManagmentExceptions;
	public void remove (long driverId);
	public void update (Driver driver) throws DriverManagmentExceptions;
	public Driver findById (Long id) throws DriverManagmentExceptions;
	public void changeStatusToActive(Long driverId) throws DriverManagmentExceptions;
	public void changeStatusToInactive(Long driverId) throws DriverManagmentExceptions;
	public void changeStatusToDelivering(Long driverId) throws DriverManagmentExceptions;
	
	public List<Driver> findAllDrivers();
	public List<Driver> findAllActive();
	public List<Driver> findAllDelivering();
	public List<Driver> findAllInActive();
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime);
	
	
	
	
}
