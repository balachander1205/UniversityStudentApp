package com.api.university.repository;

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
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into students (universityname, studentname, location, studentlocation, phonenumber, email, feedback, createdatetime, profilepic, passoutyear) " +
            "values (:universityname, :studentname, :location, :studentlocation, :phonenumber, :email, :feedback, :createdatetime, :profilepic, :passoutyear)", nativeQuery = true)
    public void insertStudent(@Param("universityname") String universityname, @Param("studentname") String studentname, @Param("location") String location,
                              @Param("studentlocation") String studentlocation, @Param("phonenumber") String phonenumber, @Param("email") String email, @Param("feedback") String feedback,
                              @Param("createdatetime") Timestamp createdatetime, @Param("profilepic") String profilepic, @Param("passoutyear") String passoutyear);

    @Transactional
    @Modifying
    @Query(value = "update table students set email=:email where phonenumber=:phonenumber", nativeQuery = true)
    public void updateStudent(@Param("phonenumber") String phonenumber, @Param("email") String email);

    @Query(value = "select data from StudentEntity data")
    public List<StudentEntity> getAllStudents();

    @Query(value = "select data from StudentEntity data")
    public List<StudentEntity> getTotalStudents();

    @Query(value = "select data from StudentEntity data where data.activestatus=1")
    public List<StudentEntity> getActiveStudents();

    @Query(value = "select data from StudentEntity data where data.phonenumber=:phonenumber")
    public List<StudentEntity> getStudentDetailsByMobileNumber(@Param("phonenumber") String phonenumber);

    @Transactional
    @Modifying
    @Query(value = "update StudentEntity data set data.feedback=:feedback where data.id=:id")
    public int updateFeedback(@Param("id") long id, @Param("feedback") String feedback);
}
