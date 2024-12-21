package com.api.university.model;

import com.api.university.entity.AppointmentsEntity;
import com.api.university.entity.LeavesEntity;
import com.api.university.entity.RepresentativeEntity;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EventsResponseModel {
    @JsonProperty("appointmenyts")
    private List<AppointmentsEntity> appointments;
    @JsonProperty("slots")
    private ArrayList<String> slots;
    @JsonProperty("representative")
    private RepresentativeEntity representative;
    @JsonProperty("leaves")
    private List<LeavesEntity> leaves;
}
