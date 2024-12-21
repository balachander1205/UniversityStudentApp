package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class StudentModel {
    private String studentname;
    private String universityname;
    private String phonenumber;
    private String studentlocation;
    private String location;
    private String email;
    private String feedback;
    private String passoutyear;
}