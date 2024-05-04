package com.api.university.controller.login;

import com.api.university.entity.UniversityEntity;
import com.api.university.model.ForgotPasswordResponse;
import com.api.university.model.LoginModel;
import com.api.university.model.ResetPassword;
import com.api.university.repository.UniversityRepository;
import com.api.university.service.EmailService;
import com.api.university.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @Autowired
    UniversityRepository usersRepository;

    @Autowired
    EmailService emailService;

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity authenticate(@RequestBody LoginModel loginModel){
        String status = "";
        try{
            log.info("loginModel={}",loginModel.getPassword());
            String password = loginModel.getPassword();
            String username = loginModel.getUsername();
            List<UniversityEntity> userEntity = usersRepository.authenticate(username);
            log.info("Username:={} Password:={}",username, password);
            if(userEntity.get(0).getUsername().equals(username) && userEntity.get(0).getPassword().equals(password)){
                status = "OK";
            }else {
                status = "NOT OK";
            }
        }catch (Exception e){
            e.printStackTrace();
            log.debug("authenticate:Xception={}",e);
        }
        return ResponseEntity.ok(status);
    }

    @PostMapping("/forgotPassword")
    @ResponseBody
    public ResponseEntity forgotPassword(@RequestParam("email") String username, HttpServletRequest httpServletRequest){
        String status = "";
        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
        String host = httpServletRequest.getHeader(HttpHeaders.HOST);
        try{
            log.info("forgotPassword:username={}",username);
            List<UniversityEntity> userEntity = usersRepository.authenticate(username);
            log.info("forgotPassword:Username:={} host={}", username, host);
            if(!userEntity.isEmpty() && userEntity.get(0).getUsername().equals(username)){
                forgotPasswordResponse.setMessage(Constants.MSG_FORGOT_PASSWORD_SUCCESS);
                forgotPasswordResponse.setUserExists(true);
                String encodedString = Base64.getEncoder().encodeToString(username.getBytes());
                forgotPasswordResponse.setResetLink("http://"+host+"/api/resetPassword?data="+encodedString);
                emailService.sendHtmlEmail(username, forgotPasswordResponse.getResetLink());
            }else {
                forgotPasswordResponse.setMessage(Constants.MSG_FORGOT_PASSWORD_FAILED);
                forgotPasswordResponse.setUserExists(false);
            }
            log.info("forgotPassword:forgotPasswordResponse={}",forgotPasswordResponse);
        }catch (Exception e){
            e.printStackTrace();
            log.debug("forgotPassword:Xception={}",e);
        }
        return ResponseEntity.ok(forgotPasswordResponse);
    }

    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam("data") String data, Model model){
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(data);
            String decodedEmail = new String(decodedBytes);
            log.info("resetPassword:email={}",decodedEmail);
            List<UniversityEntity> userEntity = usersRepository.authenticate(decodedEmail);
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(decodedEmail);
            model.addAttribute("user", resetPassword);
        }catch (Exception e){
            e.printStackTrace();
            log.debug("resetPassword:Xception={}",e);
        }
        return "resetpassword";
    }

    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(Model model,@Valid ResetPassword user, BindingResult bindingResult){
        log.info("{}",model);
        log.info("User={}",model.getAttribute("email"));
        user.setEmail(user.getEmail());
        model.addAttribute("user", user);
        List<UniversityEntity> userEntity = usersRepository.authenticate(user.getEmail());
        log.info("resetPassword:userEntity={}",userEntity);
        if(userEntity.size()>0){
            usersRepository.resetPassword(user.getEmail(), user.getNewPassword());
            model.addAttribute("successMessage", "Password rest successful.");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "Password reset failed.");
            model.addAttribute("bindingResult", bindingResult);
            return "register";
        }
        return "resetpassword";
    }
}

