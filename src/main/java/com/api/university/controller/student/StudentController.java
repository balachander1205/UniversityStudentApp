package com.api.university.controller.student;

import com.api.university.entity.StudentEntity;
import com.api.university.model.StudentModel;
import com.api.university.repository.StudentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/createStudent")
    public ResponseEntity createStudent(@RequestBody StudentModel studentModel) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<StudentEntity> isStudentExists = studentRepository.getStudentDetailsByMobileNumber(studentModel.getPhonenumber());
        if(isStudentExists.size()==0){
            studentRepository.insertStudent(studentModel.getUniversityname(), studentModel.getStudentname(),
                    studentModel.getLocation(), studentModel.getStudentlocation(),
                    studentModel.getPhonenumber(), studentModel.getEmail(), studentModel.getFeedback(), timestamp, studentModel.getPassoutyear());
            return ResponseEntity.ok("OK");
        }else{
            return ResponseEntity.ok("Student with mobile number already exists...");
        }
    }

    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        studentRepository.updateStudent(phoneNumber, email);
        return ResponseEntity.ok("Student update successful...");
    }

    @GetMapping("/studentDetails")
    public String studentDetails(HttpSession httpSession, ModelMap modelMap, RedirectAttributes redir) {
        return "students";
    }

    @PostMapping("/getAllStudents")
    public ResponseEntity getAllStudents(){
        return ResponseEntity.ok(studentRepository.getAllStudents());
    }

    @PostMapping("/getStudentDetailsByMobileNumber")
    @ResponseBody
    public ResponseEntity getStudentDetailsByMobileNumber(@RequestParam("phoneNumber") String phoneNumber){
        return ResponseEntity.ok(studentRepository.getStudentDetailsByMobileNumber(phoneNumber));
    }

    @PostMapping("/isUserExists")
    @ResponseBody
    public ResponseEntity isUserExists(@RequestParam("phoneNumber") String phoneNumber){
        List<StudentEntity> data = studentRepository.getStudentDetailsByMobileNumber(phoneNumber);
        if(data.size()>0){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/giveFeedback")
    @ResponseBody
    public ResponseEntity giveFeedback(@RequestParam("id") long id,@RequestParam("feedback") String feedback){
        int count = studentRepository.updateFeedback(id, feedback);
        if(count>0){
            return ResponseEntity.ok("Feedback updated successfully.");
        }
        return ResponseEntity.ok("Feedback update failed.");
    }
}
