package com.api.university.controller.university;

import com.api.university.entity.UniversityEntity;
import com.api.university.model.RepresentativesResponseModel;
import com.api.university.model.UniversityModel;
import com.api.university.model.UniversityResponseModel;
import com.api.university.repository.UniversityRepository;
import com.api.university.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/getUniversitiesByID", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUniversitiesByID(@RequestParam("id") String id){
        List<UniversityEntity> allUniversities = universityService.getUniversitiesByID(Integer.valueOf(id));

        JSONArray universities = new JSONArray();
        for(UniversityEntity university : allUniversities){
            JSONObject rep = new JSONObject();
            rep.put("repName", university.getRepname());
            rep.put("id", university.getId());
            rep.put("description", university.getDescription());
            rep.put("location", university.getLocation());
            rep.put("position", university.getPosition());
            rep.put("universityName", university.getUniversityname());
            rep.put("admissionIntake", university.getAdmissionintake());
            universities.put(rep);
        }
        return universities.toString();
    }

    @PostMapping(value = "/getRepresentatives", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRepresentatives(){
        List<UniversityEntity> allUniversities = universityService.getAllUniversities();
        JSONArray allReps = new JSONArray();
        for(UniversityEntity university : allUniversities){
            JSONObject rep = new JSONObject();
            rep.put("repname", university.getRepname());
            allReps.put(rep);
        }
        log.info("reps={}",allReps);
        return allReps.toString();
    }

}
