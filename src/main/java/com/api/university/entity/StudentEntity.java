package com.api.university.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "universityname")
    private String universityname;
    @Column(name = "studentname")
    private String studentname;
    @Column(name = "location")
    private String location;
    @Column(name = "studentlocation")
    private String studentlocation;
    @Column(name = "phonenumber")
    private String phonenumber;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "createdatetime")
    private String createdatetime;
    @Column(name = "activestatus")
    private String activestatus;
    @Column(name = "passoutyear")
    private String passoutyear;
}
