package com.api.university.service;


import java.sql.Timestamp;

public interface StudentPreferenceService {
    public void savePreference(String studentname, String phonenumber, String email, String country, String state,
                               Timestamp createdatetime, String yearofplan, String coursetype);
}
