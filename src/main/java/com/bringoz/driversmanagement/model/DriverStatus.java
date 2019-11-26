package com.bringoz.driversmanagement.model;

import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public enum DriverStatus {

	 ACTIVE("ACTIVE"),
	 INACTIVE("INACTIVE"), 
	 DELIVERING("DELIVERING");
 
    private String name;
 
    
    private DriverStatus (String name) {
        this.name = name;
    }
}
