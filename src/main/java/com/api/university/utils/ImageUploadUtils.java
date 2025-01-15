package com.api.university.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageUploadUtils {
    public String uploadImageToImgBB(String image){
        String imageURL = "";
        try{
            String name = UUID.randomUUID().toString();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("https://api.imgbb.com/1/upload?key=338d7a198d571234a89852d4ab61bf26&name="+name)
                    .field("image", image)
                    .asString();
            System.out.println(response);
            JSONObject data = new JSONObject(response.getBody().toString());
            JSONObject dataObj = data.getJSONObject("data");
            imageURL = dataObj.getString("display_url");
        }catch (Exception e){
            e.printStackTrace();
        }
        return imageURL;
    }
}
