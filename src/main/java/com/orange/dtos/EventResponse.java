package com.orange.dtos;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;


@Data
public class EventResponse {

    @CsvDate(value = "dd/MM/yyyy hh:mm")
    @CsvBindByName(column = "event_start_date")
    private Date startDate;

    @CsvDate(value = "dd/MM/yyyy hh:mm")
    @CsvBindByName(column = "event_end_date")
    private Date endDate;

    @CsvBindByName(column = "event_id")
    private Integer eventId;

    @CsvBindByName(column = "event_type")
    private String eventType;

    @CsvBindByName(column = "event_status")
    private String eventStatus;

    @CsvBindByName(column = "tech_id")
    private String techId;

    @CsvBindByName(column = "tech_last_name")
    private String techLastName;

    @CsvBindByName(column = "tech_first_name")
    private String techFirstName;

    @CsvBindByName(column = "tech_mail")
    private String techMail;

    @CsvBindByName(column = "tech_phone")
    private String techPhone;

    @CsvBindByName(column = "customer_id")
    private String customerId;

    @CsvBindByName(column = "customer_last_name")
    private String customerLastName;

    @CsvBindByName(column = "customer_first_name")
    private String customerFirstName;

    @CsvBindByName(column = "customer_mail")
    private String customerMail;

    @CsvBindByName(column = "customer_phone")
    private String customerPhone;

    @CsvBindByName(column = "customer_country")
    private String customerCountry;

    @CsvBindByName(column = "customer_city")
    private String customerCity;

    @CsvBindByName(column = "customer_postcode")
    private String customerPostcode;

    @CsvBindByName(column = "customer_street")
    private String customerStreet;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "teammates_number")
    private String teammatesNum;

    @CsvBindByName(column = "event_req")
    private String eventReq;

    @Override
    public String toString() {
        return "CommonResponse{" +
                "eventId='" + eventId + '\'' +
                "description='" + description + '\'' +
                "techId='" + techId + '\'' +
                "customerId='" + customerId + '\'' +
                '}';
    }
}
