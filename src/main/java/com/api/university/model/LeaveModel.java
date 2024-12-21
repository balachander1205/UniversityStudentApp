package com.api.university.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveModel {
    private String eventId;
    private String repEmail;
    private String startDate;
    private String endDate;
    private String title;
}
