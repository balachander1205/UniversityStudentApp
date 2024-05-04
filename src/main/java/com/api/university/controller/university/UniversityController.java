package com.api.university.controller.university;

import com.api.university.entity.UniversityEntity;
import com.api.university.model.UniversityModel;
import com.api.university.model.UniversityResponseModel;
import com.api.university.repository.UniversityRepository;
import com.api.university.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@Slf4j
public class UniversityController {

    @Autowired
    UniversityRepository universityService;

    @PostMapping("/getAllUniversities")
    public ResponseEntity getAllUniversities(){
        List<UniversityEntity> allUniversities = universityService.getAllUniversities();
        UniversityResponseModel universityResponseModel = new UniversityResponseModel();
        universityResponseModel.setUniversities(allUniversities);
        Map arrayList = new HashMap();
        allUniversities.forEach((entity)->{
            arrayList.put(entity.getRepname(), entity.getRepname());
        });
        log.info("reps={}",arrayList);
        //List<String> representatives = universityService.getAllUniversities().stream().map(UniversityEntity :: getRepname).collect(Collectors.toList());
        universityResponseModel.setRepresentatives(arrayList);
        log.info("universityResponseModel={}",universityResponseModel);
        return ResponseEntity.ok(universityResponseModel);
    }

    @PostMapping("/addUniversity")
    public ResponseEntity addUniversity(@RequestBody UniversityModel universityModel){
        universityService.insertUniversity(universityModel.getUniversityname(), universityModel.getDescription(),
                universityModel.getLocation(), universityModel.getRepname(), universityModel.getRepname(),
                universityModel.getAdmissionintake(), universityModel.getUsername(), universityModel.getPassword());

        List<UniversityEntity> allUniversities = universityService.getAllUniversities();
        UniversityResponseModel universityResponseModel = new UniversityResponseModel();
        universityResponseModel.setUniversities(allUniversities);
        Map arrayList = new HashMap();
        allUniversities.forEach((entity)->{
            arrayList.put(entity.getRepname(), entity.getRepname());
        });
        universityResponseModel.setStatus(HttpStatus.ACCEPTED.toString());
        universityResponseModel.setMessage(Constants.MSG_NEW_UNIVERSITY_SUCCESS.replace("%s", universityModel.getUniversityname()));
        return ResponseEntity.ok(universityResponseModel);
    }

    @PostMapping("/getUniversitiesByRepName")
    public ResponseEntity getUniversitiesByRepName(@RequestParam("repname") String repName){
        List<UniversityEntity> allUniversities = universityService.getUniversitiesByRepName(repName);
        UniversityResponseModel universityResponseModel = new UniversityResponseModel();
        universityResponseModel.setUniversities(allUniversities);
        Map arrayList = new HashMap();
        allUniversities.forEach((entity)->{
            arrayList.put(entity.getRepname(), entity.getRepname());
        });
        log.info("reps={}",arrayList);
        //List<String> representatives = universityService.getAllUniversities().stream().map(UniversityEntity :: getRepname).collect(Collectors.toList());
        universityResponseModel.setRepresentatives(arrayList);
        log.info("universityResponseModel={}",universityResponseModel);
        return ResponseEntity.ok(universityResponseModel);
    }


}
