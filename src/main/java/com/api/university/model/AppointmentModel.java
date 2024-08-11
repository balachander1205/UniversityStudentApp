package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentModel {
    private String studentName;
    private String repName;
    private String universityName;
    private String location;
    private String appointmentDate;
    private String appointmentSlot;
    private String phoneNumber;
}
