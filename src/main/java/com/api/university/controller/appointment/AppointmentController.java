package com.api.university.controller.appointment;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.LeavesEntity;
import com.api.university.entity.RepresentativeEntity;
import com.api.university.model.AppointmentModel;
import com.api.university.model.EventsResponseModel;
import com.api.university.repository.AppointmentsRepository;
import com.api.university.service.LeaveService;
import com.api.university.service.RepresentativeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @Autowired
    RepresentativeService representativeService;

    @Autowired
    LeaveService leaveService;

    @PostMapping("/bookAppointment")
    public ResponseEntity bookAppointment(@RequestBody AppointmentModel appointmentModel){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        appointmentsRepository.createAppointment(appointmentModel.getStudentName(), appointmentModel.getRepEmail(),
                appointmentModel.getUniversityName(), appointmentModel.getLocation(), appointmentModel.getAppointmentDate(), appointmentModel.getAppointmentSlot(), timestamp,
                appointmentModel.getPhoneNumber());
        return ResponseEntity.ok(appointmentsRepository.getAllAppointments());
    }

    @PostMapping("/getAllAppointments")
    public ResponseEntity bookAppointment(){
        return ResponseEntity.ok(appointmentsRepository.getAllAppointments());
    }

    @PostMapping("/getUpcomingAppointments")
    public ResponseEntity getUpcomingAppointments(){
        return ResponseEntity.ok(appointmentsRepository.getUpcomingAppointments());
    }

    @PostMapping("/getAppointmentsByRepname")
    public ResponseEntity getAppointmentsByRepname(@RequestParam("repname") String repname){
        List<AppointmentsEntity> listOfAppointments = appointmentsRepository.getAppointmentsByRepname(repname);
        return ResponseEntity.ok(listOfAppointments);
    }

    @PostMapping("/getAppointmentsByID")
    public ResponseEntity getAppointmentsByID(@RequestParam("id") String id){
        List<AppointmentsEntity> listOfAppointments = appointmentsRepository.getAppointmentsByID(id);
        return ResponseEntity.ok(listOfAppointments);
    }

    @PostMapping("/getAppointmentDetailsByID")
    public ResponseEntity getAppointmentDetailsByID(@RequestParam("id") String id){
        List<AppointmentsEntity> listOfAppointments = appointmentsRepository.getAppointmentsByID(id);
        return ResponseEntity.ok(listOfAppointments);
    }

    @PostMapping("/getAppointmentsWithMobileNumber")
    public ResponseEntity getAppointmentsWithMobileNumber(@RequestParam("phoneNumber") String phoneNumber){
        List<AppointmentsEntity> listOfAppointments = appointmentsRepository.getAppointmentsWithMobileNumber(phoneNumber);
        return ResponseEntity.ok(listOfAppointments);
    }

    @PostMapping("/getAppointmentsByRepEmail")
    public ResponseEntity getAppointmentsByRepEmail(@RequestParam("email") String email){
        List<AppointmentsEntity> listOfAppointments = appointmentsRepository.getAppointmentsByRepEmail(email);
        return ResponseEntity.ok(listOfAppointments);
    }

    @PostMapping("/getSlotsByDateForRepEmail")
    @ResponseBody
    public ResponseEntity getAvailableSlotsByRepEmailDate(@RequestParam("email") String email, @RequestParam("date") String date){
        ArrayList<String> slots = new ArrayList<>();
        RepresentativeEntity representativeEntity = representativeService.getRepresentativeByEmail(email);
        String availability[] = representativeEntity.getAvailability().trim().split("-");
        System.out.println("Availability=" + representativeEntity.getAvailability());
        String start = availability[0].trim();
        String end = availability[1].trim();

        String startTime[] = start.split(":");
        String endTime[] = end.split(":");
        int i = Integer.parseInt(startTime[0].trim());
        int j = Integer.parseInt(endTime[0].trim());
        for(int k=i;k<=j;k++){
            if((k+1)>j){
                break;
            }else {
                slots.add(k + ":00-" + (k + 1) + ":00");
            }
        }
        System.out.println(slots);

        List<AppointmentsEntity> upcomingAppointments = appointmentsRepository.getUpcomingAppointmentsByRepID(email);
        System.out.println("upcomingAppointments="+upcomingAppointments);
        if(upcomingAppointments.size()>0){
            upcomingAppointments.stream().forEach(appointment->{
                slots.remove(appointment.getAppointmentslot());
            });
        }

        List<LeavesEntity> leaves = leaveService.getUpcomingLeavesByRepEmail(email);
        EventsResponseModel eventsResponseModel = new EventsResponseModel();
        eventsResponseModel.setAppointments(upcomingAppointments);
        eventsResponseModel.setSlots(slots);
        eventsResponseModel.setRepresentative(representativeEntity);
        eventsResponseModel.setLeaves(leaves);
        return ResponseEntity.ok(eventsResponseModel);
    }

    @PostMapping("/getUpcomingAppointmentsByRepEmail")
    public ResponseEntity getUpcomingAppointmentsByRepEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(appointmentsRepository.getUpcomingAppointmentsByRepID(email));
    }
}
