package com.api.university.controller.university;

import com.api.university.entity.RepresentativeEntity;
import com.api.university.entity.UniversityEntity;
import com.api.university.model.UniversityModel;
import com.api.university.model.UniversityResponseModel;
import com.api.university.repository.UniversityRepository;
import com.api.university.service.RepresentativeService;
import com.api.university.utils.CommonUtils;
import com.api.university.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/api")
@Slf4j
public class UniversityController {

    @Autowired
    UniversityRepository universityService;

    @Autowired
    RepresentativeService representativeService;

    @Value("${spring.file.upload.location}")
    public String fileUploadLocation;

    @Autowired
    CommonUtils commonUtils;

    @PostMapping("/getAllUniversities")
    public ResponseEntity getAllUniversities() {
        List<UniversityEntity> allUniversities = universityService.getAllUniversities();
        String homeURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        UniversityResponseModel universityResponseModel = new UniversityResponseModel();
        universityResponseModel.setUniversities(allUniversities);
        universityResponseModel.getUniversities().stream().forEach(data -> {
            List<String> imagesList = new ArrayList<>();
            // Get representatives of university
            if (data.getUniversityid() != null) {
                List<RepresentativeEntity> reps = representativeService.getRepresentativeByUniversityId(data.getUniversityid());
                data.setRepresentativeEntities(reps);
            }
            if (data.getImages() != null) {
                if (data.getImages() != null && data.getImages().length() > 0 && data.getImages().contains(",")) {
                    String[] images = data.getImages().split(",");
                    Arrays.stream(images).forEach(img -> {
                        //String actualImage = homeURL + "/api/images/" + img;
                        String actualImage = img;
                        imagesList.add(actualImage);
                    });
                } else {
                    //String actualImage = homeURL + "/api/images/" + data.getImages();
                    String actualImage = data.getImages();
                    imagesList.add(actualImage);
                }
                data.setImages(StringUtils.join(imagesList, ','));
            }
        });
        log.info("universityResponseModel={}", universityResponseModel);
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

            long currentTS = System.currentTimeMillis();
            String universityID = String.valueOf(currentTS);

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
            // Add university
//            universityService.insertUniversity(universityModel.getUniversityname(), universityModel.getDescription(),
//                    universityModel.getLocation(), universityModel.getRepname(), universityModel.getRepname(),
//                    universityModel.getAdmissionintake(), "", "", universityModel.getState(), allImages);
            universityService.insertUniversity(universityModel.getUniversityname(), universityModel.getDescription(),
                    universityModel.getLocation(), universityModel.getRepname(), universityModel.getRepname(),
                    universityModel.getAdmissionintake(), universityModel.getUsername(), universityModel.getPassword(), universityModel.getState(), "",
                    universityModel.getCourse(), universityModel.getIsRecommended(), universityID);

            // Add representative
            representativeService.createRepresentative(universityModel.getRepname(), universityModel.getEmail(), universityModel.getPhoneNumber(),
                    allImages, universityModel.getUsername(), universityModel.getPassword());

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
//                            String actualImage = img;
                            imagesList.add(actualImage);
                        });
                    } else {
                        String actualImage = homeURL + "/api/images/" + data.getImages();
//                        String actualImage = data.getImages();
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

    @PostMapping("/getUniversitiesByRepEmail")
    public ResponseEntity getUniversitiesByRepEmail(@RequestParam("repEmail") String email){
        RepresentativeEntity rep = representativeService.getRepresentativeByEmail(email);
        UniversityResponseModel universityResponseModel = new UniversityResponseModel();

        if (rep != null) {
            String universityID = rep.getUniversityid();
            List<UniversityEntity> allUniversities = universityService.getUniversitiesByID(universityID);
            List<RepresentativeEntity> reps = representativeService.getRepresentativeByUniversityId(universityID);
            if (reps.size() > 0) {
                allUniversities.forEach(universityEntity -> {
                    universityEntity.setRepresentativeEntities(reps);
                });
            }
            universityResponseModel.setUniversities(allUniversities);
        }
        log.info("universityResponseModel={}",universityResponseModel);
        return ResponseEntity.ok(universityResponseModel);
    }

    @PostMapping(value = "/getUniversitiesByID", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUniversitiesByID(@RequestParam("universityId") String id){
        List<UniversityEntity> allUniversities = universityService.getUniversitiesByID(id);

        JSONArray representatives = new JSONArray();
        JSONObject rep = new JSONObject();
        for(UniversityEntity university : allUniversities){
            rep.put("repName", university.getRepname());
            rep.put("id", university.getId());
            rep.put("description", university.getDescription());
            rep.put("location", university.getLocation());
            rep.put("position", university.getPosition());
            rep.put("universityName", university.getUniversityname());
            rep.put("admissionIntake", university.getAdmissionintake());
            rep.put("images",university.getImages());
            rep.put("universityid",university.getUniversityid());
            List<RepresentativeEntity> reps = representativeService.getRepresentativeByUniversityId(university.getUniversityid());
            if (reps.size() > 0) {
                reps.forEach(row -> {
                    JSONObject repObj = new JSONObject();
                    repObj.put("repname", row.getRepname());
                    repObj.put("email", row.getEmail());
                    repObj.put("phonenumber", row.getPhonenumber());
                    repObj.put("universityid", row.getUniversityid());
                    repObj.put("availability", row.getAvailability());
                    representatives.put(repObj);
                });
            }
            rep.put("representatives", representatives);
        }
        return rep.toString();
    }

    @PostMapping(value = "/getRepresentatives", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRepresentatives(){
        List<RepresentativeEntity> allRepresentatives = representativeService.getAllRepresentatives();
        JSONArray allReps = new JSONArray();
        for(RepresentativeEntity university : allRepresentatives){
            JSONObject rep = new JSONObject();
            rep.put("repname", university.getRepname());
            rep.put("email", university.getEmail());
            rep.put("phonenumber", university.getPhonenumber());
            rep.put("image", "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg");
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

    @GetMapping(value = "/searchUniversity")
    @ResponseBody
    public ResponseEntity searchUniversity(@RequestParam(value = "universityName", defaultValue = "") String universityName,
                                           @RequestParam(value = "location", defaultValue = "") String location,
                                           @RequestParam(value = "state", defaultValue = "") String state,
                                           @RequestParam(value = "courseType", defaultValue = "") String courseType,
                                           @RequestParam(value = "admissionInTake", defaultValue = "") String admissionInTake){
        List<UniversityEntity> data = universityService.searchUniversity(universityName, state, location, courseType, admissionInTake);
        return ResponseEntity.ok(data);
    }

}
