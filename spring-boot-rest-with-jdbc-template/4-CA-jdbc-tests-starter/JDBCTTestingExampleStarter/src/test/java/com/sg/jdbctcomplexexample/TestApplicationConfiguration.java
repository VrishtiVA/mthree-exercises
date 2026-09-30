package com.sg.jdbctcomplexexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Only exists to start up our tests
 * without running the main program.
 */
@Configuration
@ComponentScan(
    basePackages = "com.sg.jdbctcomplexexample",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = CommandLineRunner.class
    )
)
@EnableAutoConfiguration
public class TestApplicationConfiguration {}
