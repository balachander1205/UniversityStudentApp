package com.api.university.repository;

import com.api.university.entity.StudentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into db_university.students (id, universityname, studentname, location, studentlocation, phonenumber, email, feedback, createdatetime) " +
            "values (0, :universityname, :studentname, :location, :studentlocation, :phonenumber, :email, :feedback, :createdatetime)", nativeQuery = true)
    public void insertStudent(@Param("universityname") String universityname, @Param("studentname") String studentname, @Param("location") String location,
                              @Param("studentlocation") String studentlocation, @Param("phonenumber") String phonenumber, @Param("email") String email, @Param("feedback") String feedback,
                              @Param("createdatetime") Timestamp createdatetime);
}
