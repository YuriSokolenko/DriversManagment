package com.bringoz.driversmanagement.service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;
import com.bringoz.driversmanagement.repository.DriverRepository;

@Service
public class DriverServiceImpl implements IDriverService {
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public void create(Driver driver) {
		driverRepository.save(driver);
	}

	@Override
	public void remove(long driverId) {
		driverRepository.deleteById(driverId);
	}

	@Override
	public void update(Driver driver) {
		Driver updatedDriver = driverRepository.findById(driver.getId()).get();
		updatedDriver.setAddress(driver.getAddress());
		updatedDriver.setAge(driver.getAge());
		updatedDriver.setIsInMapBounds(driver.getIsInMapBounds());
		updatedDriver.setStart(driver.getStart());
		updatedDriver.setEnd(driver.getEnd());
		updatedDriver.setStatus(driver.getStatus());
		driverRepository.save(updatedDriver);
	}

	@Override
	public Driver findById(Long id) {
		return driverRepository.findById(id).get();
	}

	@Override
	public void changeStatusToActive(Long driverId) {
		Driver driver = driverRepository.findById(driverId).get();
		driver.setStatus(DriverStatus.ACTIVE);
		driverRepository.save(driver);
	}
	
	@Override
	public void changeStatusToInactive(Long driverId) {
		Driver driver = driverRepository.findById(driverId).get();
		driver.setStatus(DriverStatus.INACTIVE);
		driverRepository.save(driver);
	}
	
	@Override
	public void changeStatusToDelivering(Long driverId) {
		Driver driver = driverRepository.findById(driverId).get();
		driver.setStatus(DriverStatus.DELIVERING);
		driverRepository.save(driver);
	}

	@Override
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime){
		return driverRepository.findAllAvailable(startTime, endTime);
	}
	
	@Override
	public List<Driver> findAllDrivers() {
		return driverRepository.findAll();
	}
	
	@Override
	public List<Driver> findAllActive() {
		return driverRepository.findAllActive();
	}
	
	@Override
	public List<Driver> findAllDelivering() {
		return driverRepository.findAllDelivering();
	}

	@Override
	public List<Driver> findAllInActive() {
		return driverRepository.findAllInactive();
	}

}
