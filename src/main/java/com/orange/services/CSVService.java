package com.orange.services;

import com.orange.dtos.EventResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVService {

    List<EventResponse> getAllEvents();

    List<EventResponse> getAllEvents(MultipartFile file);
}
