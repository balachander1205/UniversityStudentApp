package com.api.university.service;

import com.api.university.entity.LeavesEntity;
import com.api.university.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    @Override
    public void createLeave(String eventid, String repid, String title, String startdate, String enddate, Timestamp createdatetime, String repemail) {
        leaveRepository.createLeave(eventid, repid, title, startdate, enddate, createdatetime, repemail);
    }

    @Override
    public List<LeavesEntity> getLeavesByRepEmail(String repEmail) {
        return leaveRepository.getLeavesByRepEmail(repEmail);
    }

    @Override
    public List<LeavesEntity> getUpcomingLeavesByRepEmail(String repEmail) {
        return leaveRepository.getUpcomingLeavesByRepEmail(repEmail);
    }

    @Override
    public void deleteLeave(String eventid) {
        leaveRepository.deleteLeave(eventid);
    }
}
