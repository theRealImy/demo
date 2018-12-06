package com.workshop.demo.controller;

import com.workshop.demo.model.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author turdeant - 21.11.18
 */
@RestController
public class WebhookController {

    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";


    @RequestMapping(value = "/map", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseWrapper> mapRequest(@RequestBody String payload) throws IOException {

        // if payload is empty, don't do anything
        if (!StringUtils.hasText(payload)) {
            new ResponseEntity<>(new ResponseWrapper(SUCCESS), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseWrapper(SUCCESS), HttpStatus.OK);

    }
}
