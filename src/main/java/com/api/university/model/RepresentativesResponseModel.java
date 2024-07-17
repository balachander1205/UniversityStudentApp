package com.api.university.model;

import com.api.university.entity.UniversityEntity;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class RepresentativesResponseModel {
    private JSONArray representatives;
    private String status;
    private String message;
}
