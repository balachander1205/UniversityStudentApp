package com.api.university.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "leaves")
@Getter
@Setter
public class LeavesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "eventid")
    private String eventid;

    @Column(name = "repid")
    private String repid;

    @Column(name = "title")
    private String title;

    @Column(name = "startdate")
    private String startdate;

    @Column(name = "enddate")
    private String enddate;

    @Column(name = "createdatetime")
    private Timestamp createdatetime;

    @Column(name = "repemail")
    private String repemail;
//    @OneToOne(optional = false, mappedBy = "email")
//    @NotFound(action = NotFoundAction.IGNORE)
////    @JoinColumn(name = "email", referencedColumnName = "repName", insertable = false, updatable = false)
//    private RepresentativeEntity address;
}
