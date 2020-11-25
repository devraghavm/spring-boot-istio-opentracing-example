package org.raghav.springbootopentracingdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo2Controller {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/demo2")
    public ResponseEntity<String> hello() {
        restTemplate.getForEntity("http://www.google.com", String.class);
        return new ResponseEntity<>("hello2", HttpStatus.OK);
    }
}
