package com.api.university.service;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.RepresentativeEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepresentativeService {
    public List<AppointmentsEntity> getRepresentativeAppointmentsByEmailID(String email);
    public List<RepresentativeEntity> getAllRepresentatives();
}
