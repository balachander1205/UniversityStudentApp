package com.api.university.service;

import com.api.university.entity.UniversityEntity;
import com.api.university.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    UniversityRepository universityRepository;

    @Override
    public List<UniversityEntity> getAllUniversities() {
        return universityRepository.getAllUniversities();
    }

    public void insertUniversity(String universityname, String description, String location,
                                 String repname, String position, String admissionintake,
                                 String username, String password, String state, String images) {
        universityRepository.insertUniversity(universityname, description, location,
                repname, position, admissionintake, username, password, state, images);
    }

    @Override
    public List<UniversityEntity> getUniversitiesByRepName(String userrname) {
        return universityRepository.getUniversitiesByRepName(userrname);
    }

    @Override
    public List<UniversityEntity> authenticate(String userrname) {
        return universityRepository.authenticate(userrname);
    }

    public void resetPassword(String username, String password){
        universityRepository.resetPassword(username, password);
    }

    @Override
    public List<UniversityEntity> getUniversitiesByID(int id) {
        return universityRepository.getUniversitiesByID(id);
    }

    public List<UniversityEntity> searchUniversity(String searchText, String state, String location){
        return universityRepository.searchUniversity(searchText, state, location);
    }
}
