package com.example.backend.authentication;

import com.example.backend.Doctor.Doctor;
import com.example.backend.Doctor.DoctorService;
import com.example.backend.Patient.Patient;
import com.example.backend.Patient.PatientService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.Patient.PatientService.*;

import java.security.SignatureException;

@RequestMapping(value = "/api/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtils jwtUtils;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    private String type;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public AuthenticationController(AuthenticationService authenticationService, JwtUtils jwtUtils) {
        this.authenticationService = authenticationService;
        this.jwtUtils = jwtUtils;
    }

    record send_otp_body(String mobile_number,String type) {
    }

    record verify_otp_body(String mobile_number, String otp) {
    }
    record send_jwt(String jwt){

    }

    @CrossOrigin
    @PostMapping("/send_otp")
    public String send_otp(@RequestBody send_otp_body send_otp_rec) {
//        return authenticationService.send_otp(send_otp_rec.mobile_number);
        if(send_otp_rec.type.equals("patient"))
        {
            if(patientService.isMobileNumberExists(send_otp_rec.mobile_number))
            {
                this.type=send_otp_rec.type;
                return "pending";
            }
            else{
                return "User Not Found";
            }
        }else if(send_otp_rec.type.equals("doctor"))
        {
            if(doctorService.isMobileNumberExists(send_otp_rec.mobile_number))
            {
                this.type=send_otp_rec.type;
                return "pending";
            }
            else{
                return "User Not Found";
            }
        }
        System.out.println(this.type);
        return "error";
    }
    @CrossOrigin
    @PostMapping("/jwt")
    public ResponseEntity<?> verify_jwt(@RequestBody String JWT) {
        JWT = JWT.substring(1, JWT.length() - 1);
        System.out.println(JWT);
        String username=get_username_using_jwt(JWT);

        System.out.println(username);
        return ResponseEntity.ok().body(username);
    }

    @CrossOrigin
    @PostMapping("/verify_otp")
    public VerifyOTPResponse verify_otp(@RequestBody verify_otp_body verify_otp_rec) {
//        String status = authenticationService.verify_otp(verify_otp_rec.otp, verify_otp_rec.mobile_number);
        String status="approved";
        if (status.equals("approved") && this.type.equals("patient")) {
            System.out.println(verify_otp_rec.mobile_number);
            if(patientService.isMobileNumberExists(verify_otp_rec.mobile_number))
            {
                Patient user = patientService.getUserByPhoneNumber(verify_otp_rec.mobile_number);
                String jwtToken = jwtUtils.generateToken(user.getPatientId());
                String username=jwtUtils.extractUsername(jwtToken);
                System.out.println(jwtToken);
                System.out.println(username);
                return new VerifyOTPResponse(status, jwtToken, user,null);
            }
            else{
                return new VerifyOTPResponse("User Not Found", null, null,null);
            }

        }
        else if(status.equals("approved") && this.type.equals("doctor")){
            System.out.println(verify_otp_rec.mobile_number);
            if(doctorService.isMobileNumberExists(verify_otp_rec.mobile_number))
            {
                Doctor user = doctorService.getDoctorByPhoneNumber(verify_otp_rec.mobile_number);
                String jwtToken = jwtUtils.generateToken(user.getDoctorId());
                String username=jwtUtils.extractUsername(jwtToken);
                System.out.println(jwtToken);
                System.out.println(username);
                return new VerifyOTPResponse(status, jwtToken,null, user);
            }
            else{
                return new VerifyOTPResponse("User Not Found", null, null,null);
            }
        }
        else {
            return new VerifyOTPResponse("nope", null, null,null);
        }
    }

    public String get_username_using_jwt(String token) {
        try {
            return jwtUtils.extractUsername(token);
        } catch (ExpiredJwtException ex) {
            return "1";
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {

            return"2";
        }
    }

}
