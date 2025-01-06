package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPreferenceModel {
    private String studentName;
    private String phoneNumber;
    private String email;
    private String country;
    private String state;
    private String courseType;
    private String yearOfPlan;
    private String inTakeType;
}