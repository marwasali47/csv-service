package com.orange.controllers;

import com.orange.dtos.EventResponse;
import com.orange.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/csv")
public class CSVController {


    @Autowired
    private CSVService csvService;

    @GetMapping(value = { "/entity" })
    public ResponseEntity<List<EventResponse>> listVideos(Locale locale){

        return new ResponseEntity<>(csvService.getAllEvents(), HttpStatus.OK);
    }

}
