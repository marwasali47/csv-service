package com.orange.services.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.orange.dtos.EventResponse;
import com.orange.exceptions.CSVException;
import com.orange.services.CSVService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CSVServiceImpl implements CSVService {

    private Logger logger = LogManager.getLogger(CSVServiceImpl.class);

    @Value("${events.files.dir}")
    private  String eventsDirectory;

    @Override
    public List<EventResponse> getAllEvents() {

        List<EventResponse> allEvents =  new ArrayList<>();
        try {
            URI uri = new URI(eventsDirectory);
            File folder = new File(uri);
            logger.debug("folder created");

            for (File file : folder.listFiles()) {
                logger.debug("inside filename : " + file.getName());
                InputStream fileStream = new FileInputStream(file);
                Reader reader = new BufferedReader(new InputStreamReader(fileStream));
                // create csv bean reader
                CsvToBean<EventResponse> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(EventResponse.class)
                        //.withIgnoreLeadingWhiteSpace(true)
                        .withSeparator(';')
                        .build();

                // convert `CsvToBean` object to list of events
                List<EventResponse> events = csvToBean.parse();
                logger.debug("events size: " + events.size());
                allEvents.addAll(events);
            }
            return allEvents;
        } catch (URISyntaxException | FileNotFoundException e) {
            logger.debug("can't parse csv file : " + e.getMessage());
            throw new CSVException("can't parse csv file ");
        }
    }

    @Override
    public List<EventResponse> getAllEvents(MultipartFile file) {
        List<EventResponse> allEvents =  new ArrayList<>();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            // create csv bean reader
            CsvToBean<EventResponse> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(EventResponse.class)
                    //.withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            // convert `CsvToBean` object to list of events
            List<EventResponse> events = csvToBean.parse();
            logger.debug("events size: " + events.size());
            allEvents.addAll(events);
        } catch (IOException e) {
            logger.debug("can't parse csv file : " + e.getMessage());
            throw new CSVException("can't parse csv file ");
        }
        return allEvents;

    }
}
