package com.api.university.repository;

import com.api.university.entity.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UniversityRepository extends CrudRepository<UniversityEntity, Long> {
    @Query("select data from UniversityEntity data")
    public List<UniversityEntity> getAllUniversities();

    @Query("select data from UniversityEntity data where data.repname=:repname")
    public List<UniversityEntity> getUniversitiesByRepName(@Param("repname") String userrname);

    @Query(value = "select data from UniversityEntity data where data.username=:username")
    public List<UniversityEntity> authenticate(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "insert into university (universityname, description, location, repname, position, admissionintake, username, password) " +
            "values (:universityname, :description, :location, :repname, :position, :admissionintake, :username, :password)" , nativeQuery = true)
    public void insertUniversity(@Param("universityname") String universityname, @Param("description") String description, @Param("location") String location,
                                 @Param("repname") String repname, @Param("position") String position, @Param("admissionintake") String admissionintake,
                                 @Param("username") String username, @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "update db_university.university set password=:password where username=:username" , nativeQuery = true)
    public void resetPassword(@Param("username") String username, @Param("password") String password);
}
