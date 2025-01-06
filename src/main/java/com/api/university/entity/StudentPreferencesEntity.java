package com.api.university.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "std_preferences")
@Getter
@Setter
public class StudentPreferencesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "studentname")
    private String studentname;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "createdatetime")
    private String createdatetime;

    @Column(name = "yearofplan")
    private String yearofplan;

    @Column(name = "coursetype")
    private String coursetype;
}
