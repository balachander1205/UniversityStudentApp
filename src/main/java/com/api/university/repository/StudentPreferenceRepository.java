package com.api.university.repository;

import com.api.university.entity.StudentPreferencesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Repository
public interface StudentPreferenceRepository extends CrudRepository<StudentPreferencesEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into std_preferences (studentname, phonenumber, email, country, state, createdatetime, yearofplan, coursetype, intaketype) " +
            "values (:studentname, :phonenumber, :email, :country, :state, :createdatetime, :yearofplan, :coursetype, :intaketype)", nativeQuery = true)
    public void savePreference(@Param("studentname") String studentname, @Param("phonenumber") String phonenumber,
                               @Param("email") String email, @Param("country") String country, @Param("state") String state,
                              @Param("createdatetime") Timestamp createdatetime, @Param("yearofplan") String yearofplan,
                               @Param("coursetype") String coursetype,@Param("intaketype") String intaketype);
}
