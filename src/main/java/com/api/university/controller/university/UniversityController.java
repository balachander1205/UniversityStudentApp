package com.api.university.controller.university;

import com.api.university.entity.UniversityEntity;
import com.api.university.model.RepresentativesResponseModel;
import com.api.university.model.UniversityModel;
import com.api.university.model.UniversityResponseModel;
import com.api.university.repository.UniversityRepository;
import com.api.university.utils.CommonUtils;
import com.api.university.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import javax.activation.FileTypeMap;
import javax.annotation.Resource;
import javax.mail.Multipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
@RequestMapping("/api")
@Slf4j
public class UniversityController {

    @Autowired
    UniversityRepository universityService;

    @Value("${spring.file.upload.location}")
    public String fileUploadLocation;

    @Autowired
    CommonUtils commonUtils;

    @PostMapping("/getAllUniversities")
    public ResponseEntity getAllUniversities(){
        List<UniversityEntity> allUniversities = universityService.getAllUniversities();
        String homeURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        UniversityResponseModel universityResponseModel = new UniversityResponseModel();
        universityResponseModel.setUniversities(allUniversities);
        universityResponseModel.getUniversities().stream().forEach(data-> {
            List<String> imagesList = new ArrayList<>();
            if(data.getImages()!=null) {
                if (data.getImages() != null && data.getImages().length() > 0 && data.getImages().contains(",")) {
                    String[] images = data.getImages().split(",");
                    Arrays.stream(images).forEach(img -> {
                        String actualImage = homeURL + "/api/images/" + img;
                        imagesList.add(actualImage);
                    });
                } else {
                    String actualImage = homeURL + "/api/images/" + data.getImages();
                    imagesList.add(actualImage);
                }
                data.setImages(StringUtils.join(imagesList, ','));
            }
        });
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

    /*@PostMapping("/addUniversity")
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
    }*/

    @PostMapping("/addUniversityDetails")
    public ResponseEntity addUniversityDetails(@RequestPart("university") String university, @RequestParam("file") MultipartFile[] files) {
        try {
            List<String> fileNames = new ArrayList<>();

            String allImages = "";
            // Read uploaded files
            if(files.length>0) {
                Arrays.asList(files).stream().forEach(file -> {
                    try {
                        String fileName = commonUtils.getUUID() + ".jpg";
                        Path fileNameAndPath = Paths.get(fileUploadLocation, fileName);
                        log.info("fileNameAndPath={}", fileNameAndPath);
                        Files.write(fileNameAndPath, file.getBytes());
                        fileNames.add(fileName);
                    } catch (Exception e) {
                        log.info("fileUPload Exception={}", e);
                    }
                });
                allImages = StringUtils.join(fileNames, ',');
                log.info("All Files={}", allImages);
            }

            UniversityModel universityModel = new UniversityModel();
            ObjectMapper objectMapper = new ObjectMapper();
            universityModel = objectMapper.readValue(university, UniversityModel.class);
            log.info("universityModel={}", universityModel);


            universityService.insertUniversity(universityModel.getUniversityname(), universityModel.getDescription(),
                    universityModel.getLocation(), universityModel.getRepname(), universityModel.getRepname(),
                    universityModel.getAdmissionintake(), universityModel.getUsername(), universityModel.getPassword(),allImages);

            List<UniversityEntity> allUniversities = universityService.getAllUniversities();
            String homeURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            UniversityResponseModel universityResponseModel = new UniversityResponseModel();
            universityResponseModel.setUniversities(allUniversities);
            universityResponseModel.getUniversities().stream().forEach(data-> {
                List<String> imagesList = new ArrayList<>();
                if(data.getImages()!=null) {
                    if (data.getImages() != null && data.getImages().length() > 0 && data.getImages().contains(",")) {
                        String[] images = data.getImages().split(",");
                        Arrays.stream(images).forEach(img -> {
                            String actualImage = homeURL + "/api/images/" + img;
                            imagesList.add(actualImage);
                        });
                    } else {
                        String actualImage = homeURL + "/api/images/" + data.getImages();
                        imagesList.add(actualImage);
                    }
                    data.setImages(StringUtils.join(imagesList, ','));
                }
            });
            universityResponseModel.setStatus(HttpStatus.ACCEPTED.toString());
            universityResponseModel.setMessage(Constants.MSG_NEW_UNIVERSITY_SUCCESS.replace("%s", universityModel.getUniversityname()));
            return ResponseEntity.ok(universityResponseModel);
        }catch (Exception e){
            log.info("Xception:addUniversityDetails={}",e);
        }
        return ResponseEntity.ok(null);
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

    @GetMapping("/images/{filename}")
    public ResponseEntity getImage(@PathVariable String filename) throws IOException {
        File file = new File(fileUploadLocation + filename);
        // Path to the image file
        Path path = Paths.get(fileUploadLocation + filename);
        // Load the resource
        UrlResource resource = new UrlResource(path.toUri());
        // Return ResponseEntity with image content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

}
