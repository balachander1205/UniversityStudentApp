package com.api.university.controller.leave;

import com.api.university.model.LeaveModel;
import com.api.university.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/api")
public class LeaveController {

    @Autowired
    LeaveService appointmentsRepository;

    @PostMapping("/createLeave")
    @ResponseBody
    public ResponseEntity createLeave(@RequestBody LeaveModel appointmentModel){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long currentTS = System.currentTimeMillis();
        appointmentsRepository.createLeave(String.valueOf(currentTS), appointmentModel.getRepEmail(),
                appointmentModel.getTitle(), appointmentModel.getStartDate(), appointmentModel.getEndDate(),  timestamp, appointmentModel.getRepEmail());
        return ResponseEntity.ok(appointmentsRepository.getLeavesByRepEmail(appointmentModel.getRepEmail()));
    }

    @DeleteMapping("/deleteLeave")
    @ResponseBody
    public ResponseEntity deleteLeave(@RequestBody LeaveModel appointmentModel){
        appointmentsRepository.deleteLeave(appointmentModel.getEventId());
        return ResponseEntity.ok(appointmentsRepository.getLeavesByRepEmail(appointmentModel.getRepEmail()));
    }

    @PostMapping("/getLeavesByRepEmail")
    @ResponseBody
    public ResponseEntity getLeavesByRepEmail(@RequestParam("repEmail") String repEmail){
        return ResponseEntity.ok(appointmentsRepository.getLeavesByRepEmail(repEmail));
    }

    @PostMapping("/getUpcomingLeavesByRepEmail")
    @ResponseBody
    public ResponseEntity getUpcomingLeavesByRepEmail(@RequestParam("repEmail") String repEmail){
        return ResponseEntity.ok(appointmentsRepository.getUpcomingLeavesByRepEmail(repEmail));
    }
}
