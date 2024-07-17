package com.api.university.service;

import com.api.university.entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentsService {
    public void createAppointment(String studentname, String repname, String universityname, String location,
                              String appointmentdate, String appointmentslot, Timestamp createdatetime);

    public List<AppointmentsEntity> getAllAppointments();

    public List<AppointmentsEntity> getAppointmentsByRepname(String repname);
    public List<AppointmentsEntity> getUpcomingAppointments();
}
