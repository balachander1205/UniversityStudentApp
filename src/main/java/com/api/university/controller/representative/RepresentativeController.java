package com.api.university.controller.representative;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.RepresentativeEntity;
import com.api.university.model.LoginModel;
import com.api.university.model.LoginResponseModel;
import com.api.university.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class RepresentativeController {

    @Autowired
    RepresentativeService representativeService;

    @PostMapping("/getRepresentativeAppointmentsByEmailID")
    public ResponseEntity getRepresentativeAppointmentsByEmailID(@RequestParam("email") String email){
        List<AppointmentsEntity> data = representativeService.getRepresentativeAppointmentsByEmailID(email);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/getAllRepresentatives")
    public ResponseEntity getAllRepresentatives(){
        List<RepresentativeEntity> data = representativeService.getAllRepresentatives();
        return ResponseEntity.ok(data);
    }

    @PostMapping("/authenticateRep")
    @ResponseBody
    public ResponseEntity authenticateRep(@RequestBody LoginModel loginModel){
        LoginResponseModel loginResponseModel = new LoginResponseModel();
        RepresentativeEntity data = representativeService.getRepresentativeByUsername(loginModel.getUsername());
        try {
            if (data != null) {
                if (!loginModel.getUsername().equals(data.getUsername())){
                    loginResponseModel.setStatus(false);
                    loginResponseModel.setMessage("User does not exists.");
                }
                if (loginModel.getUsername().equals(data.getUsername()) && !loginModel.getPassword().equals(data.getPassword())) {
                    loginResponseModel.setStatus(false);
                    loginResponseModel.setMessage("Invalid Password.");
                }
                if (loginModel.getUsername().equals(data.getUsername()) && loginModel.getPassword().equals(data.getPassword())) {
                    loginResponseModel.setStatus(true);
                    loginResponseModel.setMessage("Authentication Successful.");
                } else {
                    loginResponseModel.setStatus(false);
                    loginResponseModel.setMessage("Authentication Failed.");
                }
            } else {
                loginResponseModel.setStatus(false);
                loginResponseModel.setMessage("Authentication Failed.");
            }
        }catch (Exception e){
            loginResponseModel.setStatus(false);
            loginResponseModel.setMessage("Internal Server Error. Authentication Failed.");
        }
        return ResponseEntity.ok(loginResponseModel);
    }

    @PutMapping("/resetRepPassword")
    @ResponseBody
    public ResponseEntity resetRepPassword(@RequestBody LoginModel loginModel){
        RepresentativeEntity data = representativeService.getRepresentativeByUsername(loginModel.getUsername());
        LoginResponseModel loginResponseModel = new LoginResponseModel();
        if(data!=null && data.getUsername().equals(loginModel.getUsername())){
            representativeService.resetPassword(loginModel.getUsername(), loginModel.getPassword());
            loginResponseModel.setStatus(true);
            loginResponseModel.setMessage("Password reset successful.");
        }else if((data==null) || (data!=null && !data.getUsername().equals(loginModel.getUsername()))){
            loginResponseModel.setStatus(false);
            loginResponseModel.setMessage("User does not exists.");
        }else{
            loginResponseModel.setStatus(false);
            loginResponseModel.setMessage("Password reset failed.");
        }
        return ResponseEntity.ok(loginResponseModel);
    }

    @PostMapping("/getRepresentativeByEmail")
    public ResponseEntity getRepresentativeByEmail(@RequestParam("email") String email){
        RepresentativeEntity data = representativeService.getRepresentativeByEmail(email);
        return ResponseEntity.ok(data);
    }

}
