package com.project.publisher.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PublisherRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
