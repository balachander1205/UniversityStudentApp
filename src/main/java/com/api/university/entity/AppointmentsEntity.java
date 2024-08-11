package com.api.university.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class AppointmentsEntity {
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

    @Column(name = "repname")
    private String repname;

    @Column(name = "appointmentdate")
    private String appointmentdate;

    @Column(name = "appointmentslot")
    private String appointmentslot;

    @Column(name = "createdatetime")
    private Timestamp createdatetime;

    @Column(name = "phonenumber")
    private String phonenumber;
}
