package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UniversityModel {
    private String universityname;
    private String description;
    private String location;
    private String repname;
    private String position;
    private String admissionintake;
    private String username;
    private String password;
    private String state;
    private List<String> images;
}
