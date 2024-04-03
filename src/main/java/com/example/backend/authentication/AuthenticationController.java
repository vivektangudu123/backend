package com.example.backend.authentication;

import com.example.backend.Patient.Patient;
import com.example.backend.Patient.PatientService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend.Patient.PatientService.*;

@RequestMapping(value = "/api/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtils jwtUtils;
    @Autowired
    private PatientService patientService;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public AuthenticationController(AuthenticationService authenticationService, JwtUtils jwtUtils) {
        this.authenticationService = authenticationService;
        this.jwtUtils = jwtUtils;
    }

    record send_otp_body(String mobile_number) {
    }

    record verify_otp_body(String mobile_number, String otp) {
    }
    record send_jwt(String jwt){

    }

    @CrossOrigin
    @PostMapping("/send_otp")
    public String send_otp(@RequestBody send_otp_body send_otp_rec) {
//        return authenticationService.send_otp(send_otp_rec.mobile_number);
        return "pending";
    }
    @CrossOrigin
    @PostMapping("/jwt")
    public String verify_jwt(@RequestBody String JWT) {
//        if (!jwtUtils.isTokenExpired(JWT)) {
//           System.out.println(" expried");
//           return "expired";
//        }
        JWT = JWT.substring(1, JWT.length() - 1);
        System.out.println(JWT);
        String username=jwtUtils.extractUsername(JWT);

        Patient a=patientService.findBy_patientId(username);
        if (a != null) {
            System.out.println("111");
            return "1";
        }
        System.out.println("2222");
        return "2";
    }

    @CrossOrigin
    @PostMapping("/verify_otp")
    public VerifyOTPResponse verify_otp(@RequestBody verify_otp_body verify_otp_rec) {
//        String status = authenticationService.verify_otp(verify_otp_rec.otp, verify_otp_rec.mobile_number);
        String status="approved";
        if (status.equals("approved")) {
            Patient user = patientService.getUserByPhoneNumber(verify_otp_rec.mobile_number);
            String jwtToken = jwtUtils.generateToken(user.getPatientId());
            String username=jwtUtils.extractUsername(jwtToken);
            System.out.println(jwtToken);
            System.out.println(username);
            return new VerifyOTPResponse(status, jwtToken, user);

        } else {
            return new VerifyOTPResponse("nope", null, null);
        }
    }
}
