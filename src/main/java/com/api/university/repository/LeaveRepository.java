package com.api.university.repository;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.LeavesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface LeaveRepository extends CrudRepository<LeavesEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into leaves (eventid, repid, title, startdate, enddate, createdatetime, repemail) " +
            "values (:eventid, :repid, :title, :startdate, :enddate, :createdatetime,:repemail)", nativeQuery = true)
    public void createLeave(@Param("eventid") String eventid, @Param("repid") String repid, @Param("title") String title, @Param("startdate") String startdate,
                            @Param("enddate") String enddate, @Param("createdatetime") Timestamp createdatetime, @Param("repemail") String repemail);

    @Query(value = "select data from LeavesEntity data where data.repemail=:repEmail")
    public List<LeavesEntity> getLeavesByRepEmail(@Param("repEmail") String repEmail);

    @Query(value = "select l from LeavesEntity l where l.repemail=:repEmail and Date(l.startdate)>=Date(NOW())")
    public List<LeavesEntity> getUpcomingLeavesByRepEmail(@Param("repEmail") String repEmail);

    @Transactional
    @Modifying
    @Query(value = "delete from leaves where eventid=:eventid", nativeQuery = true)
    public void deleteLeave(@Param("eventid") String eventid);
}
