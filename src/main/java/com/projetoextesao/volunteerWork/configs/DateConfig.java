package com.projetoextesao.volunteerWork.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z";

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        builder.modules(new JavaTimeModule());
        return builder;
    }
}
