package com.bringoz.driversmanagement.service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;

public interface IDriverService {

	public void create (Driver driver);
	public void remove (long driverId);
	public void update (Driver driver);
	public Driver findById (Long id);
	public void changeStatusToActive(Long driverId);
	public void changeStatusToInactive(Long driverId);
	public void changeStatusToDelivering(Long driverId);
	
	public List<Driver> findAllDrivers();
	public List<Driver> findAllActive();
	public List<Driver> findAllDelivering();
	public List<Driver> findAllInActive();
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime);
	
	
	
	
}
