package com.api.university.repository;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.StudentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AppointmentsRepository extends CrudRepository<AppointmentsEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into appointments (studentname, repname, universityname, location, appointmentdate, appointmentslot, createdatetime) " +
            "values (:studentname, :repname, :universityname, :location, :appointmentdate, :appointmentslot, :createdatetime)", nativeQuery = true)
    public void createAppointment(@Param("studentname") String studentname, @Param("repname") String repname, @Param("universityname") String universityname, @Param("location") String location,
                              @Param("appointmentdate") String appointmentdate, @Param("appointmentslot") String appointmentslot, @Param("createdatetime") Timestamp createdatetime);

    @Query(value = "select data from AppointmentsEntity data")
    public List<AppointmentsEntity> getAllAppointments();

    @Query(value = "select data from AppointmentsEntity data where data.repname=:repname")
    public List<AppointmentsEntity> getAppointmentsByRepname(@Param("repname") String repname);

    @Query(value = "select data from AppointmentsEntity data where data.appointmentdate>=NOW()")
    public List<AppointmentsEntity> getUpcomingAppointments();
}
