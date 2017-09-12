package com.sprinthive.starter.json;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class ExampleDto {

    private long id;
    private String name;
    private LocalDateTime dateCreated;
    private LocalDate dateOfBirth;
}
