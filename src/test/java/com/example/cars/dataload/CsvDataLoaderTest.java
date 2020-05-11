package com.example.cars.dataload;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CsvDataLoaderTest {

    private final CsvDataLoader unitUnderTest = new CsvDataLoader();

    @Test
    void givenInputWithMultiCaseHeaders_shouldProcessToLowerCaseKeys() throws Exception {
        //given
        File input = new ClassPathResource("testData/testData.csv").getFile();

        //when
        List<Map<String, String>> result = unitUnderTest.loadObjectList(input);

        //then
        assertThat("should be converted to list of maps correctly", result, is(notNullValue()));
        assertThat("should be converted to list of maps correctly", result.size(), is(3));
        assertThat("should be converted to list of maps correctly", result.get(0), is(notNullValue()));
        assertThat("should be converted to list of maps correctly", result.get(0).keySet(), hasItem(equalTo("make")));
        assertThat("should be converted to list of maps correctly", result.get(0).keySet(), not(hasItem(equalTo("Make"))));
        assertThat("should be converted to list of maps correctly", result.get(0).keySet(), hasItem(equalTo("model")));
        assertThat("should be converted to list of maps correctly", result.get(0).keySet(), hasItem(equalTo("color")));
    }

    @Test
    void givenInputWithMalformedCsv_shouldReturnEmptyList() throws Exception {
        //given
        File input = new ClassPathResource("testData/malformedData.csv").getFile();

        //when
        List<Map<String, String>> result = unitUnderTest.loadObjectList(input);

        //then
        assertThat("should be converted to list of maps correctly", result, is(notNullValue()));
        assertThat("should be converted to list of maps correctly", result.size(), is(0));
    }

}