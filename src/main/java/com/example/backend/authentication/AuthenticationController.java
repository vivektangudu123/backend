package com.example.backend.authentication;

import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")

public class AuthenticationController {
    private final AuthenticationService authenticationService;
    // private final TokenManager tokenManager;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        // tokenManager = new TokenManager();
    }

    record send_otp_body(String mobile_number) {
    }

    record verify_otp_body(String mobile_number, String otp) {
    }

    @CrossOrigin
    @PostMapping("/send_otp")
    public String send_otp(@RequestBody send_otp_body send_otp_rec) {
        System.out.println("hi");
        return authenticationService.send_otp(send_otp_rec.mobile_number);
    }

    @CrossOrigin
    @PostMapping("/verify_otp")
    public String verify_otp(@RequestBody verify_otp_body verify_otp_rec) {
        return authenticationService.verify_otp(verify_otp_rec.otp, verify_otp_rec.mobile_number);
    }
}
