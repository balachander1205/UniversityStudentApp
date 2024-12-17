package com.api.university.service;

import com.api.university.entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentsService {
    public void createAppointment(String studentname, String repname, String universityname, String location,
                              String appointmentdate, String appointmentslot, Timestamp createdatetime, String phoneNumber);

    public List<AppointmentsEntity> getAllAppointments();

    public List<AppointmentsEntity> getAppointmentsByRepname(String repname);
    public List<AppointmentsEntity> getUpcomingAppointments();
    public List<AppointmentsEntity> getAppointmentsByID(String id);
    public List<AppointmentsEntity> getAppointmentsWithMobileNumber(String phoneNumber);
    public List<AppointmentsEntity> getAppointmentsByRepEmail(String email);
}
