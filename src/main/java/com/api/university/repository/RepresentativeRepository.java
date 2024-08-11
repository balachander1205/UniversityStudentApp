package com.api.university.repository;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.RepresentativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentativeRepository extends CrudRepository<RepresentativeEntity, Long> {

    @Query(value = "select a from RepresentativeEntity r, AppointmentsEntity a where r.email=:email and r.repname = a.repname")
    public List<AppointmentsEntity> getRepresentativeAppointmentsByEmailID(@Param("email") String email);

    @Query(value = "select r from RepresentativeEntity r")
    public List<RepresentativeEntity> getAllRepresentatives();
}
