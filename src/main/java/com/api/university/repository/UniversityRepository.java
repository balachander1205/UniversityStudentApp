package com.api.university.repository;

import com.api.university.entity.UniversityEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UniversityRepository extends CrudRepository<UniversityEntity, Long> {
    @Query("select data from UniversityEntity data")
    public List<UniversityEntity> getAllUniversities();

    @Query("select data from UniversityEntity data where data.repname=:repname")
    public List<UniversityEntity> getUniversitiesByRepName(@Param("repname") String userrname);

    @Query("select data from UniversityEntity data where data.universityid=:universityid")
    public List<UniversityEntity> getUniversitiesByID(@Param("universityid") String universityid);

    @Query(value = "select data from UniversityEntity data where data.username=:username")
    public List<UniversityEntity> authenticate(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "insert into university (universityname, description, location, repname, position, admissionintake, username, password, state, images, coursetype, isrecommended, universityid) " +
            "values (:universityname, :description, :location, :repname, :position, :admissionintake, :username, :password, :state, :images, :coursetype, :isrecommended, :universityid)" , nativeQuery = true)
    public void insertUniversity(@Param("universityname") String universityname, @Param("description") String description, @Param("location") String location,
                                 @Param("repname") String repname, @Param("position") String position, @Param("admissionintake") String admissionintake,
                                 @Param("username") String username, @Param("password") String password, @Param("state") String state, @Param("images") String images,
                                 @Param("coursetype") String coursetype, @Param("isrecommended") String isrecommended, @Param("universityid") String universityid);

    @Transactional
    @Modifying
    @Query(value = "update db_university.university set password=:password where username=:username" , nativeQuery = true)
    public void resetPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT data from UniversityEntity data WHERE data.universityname LIKE %:searchText% OR data.state LIKE %:state%  OR data.state LIKE %:location%  OR data.coursetype LIKE %:coursetype% OR data.admissionintake LIKE %:admissionintake%")
    List<UniversityEntity> searchUniversity(@Param("searchText") String searchText, @Param("state") String state, @Param("location") String location, @Param("coursetype") String coursetype, @Param("admissionintake") String admissionintake);
}
