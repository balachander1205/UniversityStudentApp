package com.api.university.controller.representative;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.RepresentativeEntity;
import com.api.university.model.LoginModel;
import com.api.university.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public boolean authenticateRep(@RequestBody LoginModel loginModel){
        RepresentativeEntity data = representativeService.getRepresentativeByUsername(loginModel.getUsername());
        if(data!=null){
            if(loginModel.getUsername().equals(data.getUsername()) && loginModel.getPassword().equals(data.getPassword())){
                return true;
            }
        }
        return false;
    }

}
