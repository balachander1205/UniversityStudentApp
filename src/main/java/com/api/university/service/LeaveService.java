package com.api.university.service;

import com.api.university.entity.LeavesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface LeaveService {
    public void createLeave(String eventid, String repid, String title, String startdate,
                            String enddate, Timestamp createdatetime, String repemail);

    public List<LeavesEntity> getLeavesByRepEmail(String repEmail);
    public List<LeavesEntity> getUpcomingLeavesByRepEmail(String repEmail);
}
