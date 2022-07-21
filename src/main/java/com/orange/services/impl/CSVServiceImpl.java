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

        logger.debug("inside getAll events");
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
                        /*.stream()
                        .filter(eventResponse -> eventResponse.getTechMail().equals(request.getUserChannel().getUsername()))
                        .map(eventResponse -> {
                            eventResponse.setUserChannelId(request.getUserChannel().getId() + "");
                            eventResponse.setChannel(request.getChannel().getName());
                            return eventResponse;
                        }).collect(Collectors.toList());*/

                logger.debug("events size: " + events.size());
                allEvents.addAll(events);
            }
            return allEvents;
        } catch (URISyntaxException | FileNotFoundException e) {
            logger.debug("can't parse csv file : " + e.getMessage());
            throw new CSVException("can't parse csv file ");
        }
    }
}
