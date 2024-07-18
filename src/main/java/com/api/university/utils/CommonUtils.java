package com.api.university.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUtils {
    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
