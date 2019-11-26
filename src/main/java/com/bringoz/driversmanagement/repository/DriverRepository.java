package com.bringoz.driversmanagement.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bringoz.driversmanagement.model.Driver;
import com.bringoz.driversmanagement.model.DriverStatus;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{

    @Query(value = "SELECT * FROM DRIVER WHERE STATUS = 'ACTIVE'", nativeQuery = true)
	List<Driver> findAllActive();

    @Query(value = "SELECT * FROM DRIVER WHERE STATUS = 'DELIVERING'", nativeQuery = true)
	List<Driver> findAllDelivering();

    @Query(value = "SELECT * FROM DRIVER WHERE STATUS = 'INACTIVE'", nativeQuery = true)
    List<Driver> findAllInactive();

    @Query("select d from Driver d where d.start <= :startTime and d.end >= :endTime and d.isInMapBounds=true and d.status = 'ACTIVE'")
	List<Driver> findAllAvailable(@Param("startTime")LocalTime startTime, @Param("endTime")LocalTime endTime);
	
	
	
	
	
}
