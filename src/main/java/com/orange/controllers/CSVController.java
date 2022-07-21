package com.orange.controllers;

import com.orange.dtos.EventResponse;
import com.orange.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/csv")
public class CSVController {


    @Autowired
    private CSVService csvService;

    @GetMapping(value = { "/parse-csv-file" })
    public ResponseEntity<List<EventResponse>> listVideos(Locale locale){

        return new ResponseEntity<>(csvService.getAllEvents(), HttpStatus.OK);
    }

    @PostMapping("/upload-csv-file")
    public ResponseEntity<List<EventResponse>> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(csvService.getAllEvents(file), HttpStatus.OK);
    }



    }
