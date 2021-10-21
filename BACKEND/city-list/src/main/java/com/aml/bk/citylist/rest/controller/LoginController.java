package com.aml.bk.citylist.rest.controller;

import com.aml.bk.citylist.rest.dto.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public ResponseEntity<Object> login(Principal principal) {
        LoginResponse response = new LoginResponse(true);
        LOGGER.info("User: {} logged", principal.getName());
        return ResponseEntity.ok(response);
    }
}
