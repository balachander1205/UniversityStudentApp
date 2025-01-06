package com.api.university.controller.student;

import com.api.university.model.SavePrefResponseModel;
import com.api.university.model.StudentPreferenceModel;
import com.api.university.service.StudentPreferenceService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        SavePrefResponseModel savePrefResponseModel = new SavePrefResponseModel();
        try {
            studentPreferenceService.savePreference(studentModel.getStudentName(), studentModel.getPhoneNumber(), studentModel.getEmail(),
                    studentModel.getCountry(), studentModel.getState(), timestamp, studentModel.getYearOfPlan(), studentModel.getCourseType());
            savePrefResponseModel.setMessage("Student preferences saved successfully.");
            savePrefResponseModel.setStatus(200);
        }catch (Exception e){
            savePrefResponseModel.setMessage("Student preferences save failed.");
            savePrefResponseModel.setStatus(500);
        }
        return ResponseEntity.ok(savePrefResponseModel);
    }
}
