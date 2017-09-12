package com.sprinthive.starter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprinthive.starter.json.ExampleDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A quick test to show how to create an object mapper that serialises LocalDateTime and LocaleDates nicely.
 */
@Slf4j
public class JsonTest {

    private ObjectMapper mapper = new ObjectMapper();
    private ExampleDto dto = ExampleDto.builder()
            .id(1505140851471L)
            .name("Testing json mapping")
            .dateCreated(LocalDateTime.now())
            .dateOfBirth(LocalDate.of(2017, 9, 11))
            .build();

    public JsonTest() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void stringify() throws JsonProcessingException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        log.info(json);
        assertThat(json).contains("Testing json mapping");
    }

    @Test
    public void fromJson() throws IOException {
        String json = "{\n" +
                "  \"id\" : 1505140851471,\n" +
                "  \"name\" : \"Testing json mapping\",\n" +
                "  \"dateCreated\" : \"2017-09-11\"\n" +
                "}";

        ExampleDto exampleDto = mapper.readValue(json, ExampleDto.class);
        assertThat(exampleDto).isEqualTo(dto);
    }
}
