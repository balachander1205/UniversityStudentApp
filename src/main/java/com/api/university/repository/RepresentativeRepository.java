package com.api.university.repository;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.RepresentativeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepresentativeRepository extends CrudRepository<RepresentativeEntity, Long> {

    @Query(value = "select a from RepresentativeEntity r, AppointmentsEntity a where r.email=:email and r.email = a.repname")
    public List<AppointmentsEntity> getRepresentativeAppointmentsByEmailID(@Param("email") String email);

    @Query(value = "select r from RepresentativeEntity r")
    public List<RepresentativeEntity> getAllRepresentatives();

    @Transactional
    @Modifying
    @Query(value = "insert into representative (repname, email, phonenumber, profilepic, username, password) " +
            "values (:repname, :email, :phonenumber, :profilepic, :username, :password)" , nativeQuery = true)
    public void createRepresentative(@Param("repname") String repname, @Param("email") String email, @Param("phonenumber") String phonenumber,
                                 @Param("profilepic") String profilepic, @Param("username") String username, @Param("password") String password);

    @Query(value = "select * from representative r where r.username=:username and r.password=:password limit 1", nativeQuery = true)
    public RepresentativeEntity getRepresentativeByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "select r from RepresentativeEntity r where r.email=:email")
    public RepresentativeEntity getRepresentativeByEmail(@Param("email") String email);

    @Query(value = "select r from RepresentativeEntity r where r.universityid=:universityid")
    public List<RepresentativeEntity> getRepresentativeByUniversityId(@Param("universityid") String universityid);

    @Transactional
    @Modifying
    @Query(value = "update RepresentativeEntity r set r.password=:password where r.username=:username")
    public int resetPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "select r from RepresentativeEntity r where r.username=:username")
    public List<RepresentativeEntity> getRepresentativeByUsername(@Param("username") String username);

}
