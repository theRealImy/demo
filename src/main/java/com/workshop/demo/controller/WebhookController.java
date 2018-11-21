package com.workshop.demo.controller;

import com.google.cloud.dialogflow.v2beta1.WebhookRequest;
import com.google.cloud.dialogflow.v2beta1.WebhookResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author turdeant - 21.11.18
 */
@RestController
public class WebhookController {


    @RequestMapping(value = "/map", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView mapRequest(@RequestBody WebhookRequest request) throws IOException {

        System.out.println(request);

        return new ModelAndView();
    }

    @RequestMapping(value = "/map2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView onlyTest(@RequestBody String request) throws IOException {

        System.out.println(request);

        return new ModelAndView("/map");
    }
}
