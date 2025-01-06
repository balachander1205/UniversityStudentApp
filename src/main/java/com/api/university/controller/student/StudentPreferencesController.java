package com.api.university.controller.student;

import com.api.university.model.StudentPreferenceModel;
import com.api.university.service.StudentPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;

@Controller
@RequestMapping("/api")
public class StudentPreferencesController {

    @Autowired
    StudentPreferenceService studentPreferenceService;

    @PostMapping("/savePreference")
    public ResponseEntity savePreference(@RequestBody StudentPreferenceModel studentModel) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        studentPreferenceService.savePreference(studentModel.getStudentName(), studentModel.getPhoneNumber(), studentModel.getEmail(),
                studentModel.getCountry(), studentModel.getState(), timestamp, studentModel.getYearOfPlan(), studentModel.getCourseType());
        return ResponseEntity.ok("Student preferences save successfully.");
    }
}
