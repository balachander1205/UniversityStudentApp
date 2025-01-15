package com.api.university.service;

import com.api.university.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface StudentService {
    public void insertStudent(String universityname, String studentname, String location,
                              String studentlocation, String phonenumber, String email, String feedback,
                              Timestamp createdatetime,String profilepic, String passoutyear);

    public List<StudentEntity> getAllStudents();

    public List<StudentEntity> getTotalStudents();

    public List<StudentEntity> getActiveStudents();

    public List<StudentEntity> getStudentDetailsByMobileNumber(String phonenumber);

    public int updateFeedback(long id, String feedback);

    public void updateStudent(String phonenumber, String email);
}
