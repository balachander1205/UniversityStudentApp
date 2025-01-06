package com.api.university.service;

import com.api.university.repository.StudentPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class StudentPreferenceServiceImpl implements StudentPreferenceService {

    @Autowired
    StudentPreferenceRepository studentPreferenceRepository;

    @Override
    public void savePreference(String studentname, String phonenumber, String email, String country, String state, Timestamp createdatetime, String yearofplan, String coursetype) {
        studentPreferenceRepository.savePreference(studentname, phonenumber, email, country, state, createdatetime, yearofplan, coursetype);
    }
}
