package com.familyshop.paybackcheckapi.controller;

import com.familyshop.paybackcheckapi.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private Environment env;

    @GetMapping("/env")
    ResponseEntity getEnvironment() {
        Response<String> response = new Response<>(HttpStatus.OK.value(), env.getProperty("env"));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
