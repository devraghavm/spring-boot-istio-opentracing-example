package org.raghav.springbootopentracingdemo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo1Controller {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/demo1")
    public ResponseEntity<String> hello(@RequestHeader HttpHeaders headers) {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://demo2-svc/demo2", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
        return new ResponseEntity<>("hello1 from demo1 & " + responseEntity.getBody() + " from demo2", HttpStatus.OK);
    }
}
