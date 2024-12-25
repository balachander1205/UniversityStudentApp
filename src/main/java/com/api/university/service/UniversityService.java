package com.api.university.service;

import com.api.university.entity.UniversityEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityService {
    public List<UniversityEntity> getAllUniversities();

    public void insertUniversity(String universityname, String description, String location,
                                 String repname, String position, String admissionintake,
                                 String username, String password, String state, String images,
                                 String coursetype, String isrecommended, String universityid);

    public List<UniversityEntity> getUniversitiesByRepName(String userrname);

    List<UniversityEntity> authenticate(String userrname);

    public void resetPassword(String username, String password);

    public List<UniversityEntity> getUniversitiesByID(String id);

    public List<UniversityEntity> searchUniversity(String searchText, String state, String location, String coursetype, String admissionintake);
}
