package com.api.university.service;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @Override
    public void createAppointment(String studentname, String repname, String universityname, String location,
                                  String appointmentdate, String appointmentslot, Timestamp createdatetime, String phoneNumber) {
        appointmentsRepository.createAppointment(studentname, repname, universityname, location, appointmentdate,
                appointmentslot, createdatetime, phoneNumber);
    }

    @Override
    public List<AppointmentsEntity> getAllAppointments() {
        return appointmentsRepository.getAllAppointments();
    }

    @Override
    public List<AppointmentsEntity> getAppointmentsByRepname(String repname) {
        return appointmentsRepository.getAppointmentsByRepname(repname);
    }

    @Override
    public List<AppointmentsEntity> getUpcomingAppointments() {
        return appointmentsRepository.getUpcomingAppointments();
    }

    @Override
    public List<AppointmentsEntity> getAppointmentsByID(String id) {
        return appointmentsRepository.getAppointmentsByID(id);
    }

    public List<AppointmentsEntity> getAppointmentsWithMobileNumber(String phoneNumber){
        return appointmentsRepository.getAppointmentsWithMobileNumber(phoneNumber);
    }

    @Override
    public List<AppointmentsEntity> getAppointmentsByRepEmail(String email) {
        return appointmentsRepository.getAppointmentsByRepEmail(email);
    }
}
