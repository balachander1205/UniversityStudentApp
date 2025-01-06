package com.api.university.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SavePrefResponseModel {
    private int status;
    private String message;
}
