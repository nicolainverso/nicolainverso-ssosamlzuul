package com.example.apidemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/example")
public class ExampleController {

    Logger logger = LoggerFactory.getLogger(ExampleController.class);
    @GetMapping("/hello")
    public ResponseEntity<String> helloMethod(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            logger.info(String.format("Header '%s' = %s", key, value));
        });
        return new ResponseEntity<>("Hello Apidemo works!", HttpStatus.OK);
    }
}
