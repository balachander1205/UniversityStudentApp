package com.api.university.service;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.RepresentativeEntity;
import com.api.university.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeServiceImpl implements RepresentativeService{
    @Autowired
    RepresentativeRepository representativeRepository;

    public List<AppointmentsEntity> getRepresentativeAppointmentsByEmailID(String email){
        return representativeRepository.getRepresentativeAppointmentsByEmailID(email);
    }

    @Override
    public List<RepresentativeEntity> getAllRepresentatives() {
        return representativeRepository.getAllRepresentatives();
    }
}
