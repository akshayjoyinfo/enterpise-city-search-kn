package com.krypton.citysearchapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.krypton.citysearchapi.controller.CityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class CitySearchApiApplicationTests {
    @Autowired
    private CityController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
