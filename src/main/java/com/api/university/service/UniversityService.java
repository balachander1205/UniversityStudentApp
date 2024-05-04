package com.api.university.service;

import com.api.university.entity.UniversityEntity;

import java.util.List;

public interface UniversityService {
    public List<UniversityEntity> getAllUniversities();
    public void insertUniversity(String universityname,String description, String location,
                                 String repname, String position, String admissionintake,
                                 String username, String password);
    public List<UniversityEntity> getUniversitiesByRepName(String userrname);
    List<UniversityEntity> authenticate(String userrname);
}
