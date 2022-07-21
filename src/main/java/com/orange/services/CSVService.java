package com.orange.services;

import com.orange.dtos.EventResponse;

import java.util.List;

public interface CSVService {

    List<EventResponse> getAllEvents();
}
