package com.assignment.cabbookingapp.Model;

import lombok.ToString;

@ToString
public class Cab {

    String cabName;
    String cabNumber;

    public Cab(String cabName, String cabNumber) {
        this.cabName = cabName;
        this.cabNumber = cabNumber;
    }
}
