package com.example.cars.dataload;

import com.example.cars.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CsvContentProcessorTest {

    @Autowired
    CsvContentProcessor csvContentProcessor;

    @MockBean
    private CsvHeaders csvHeaders;

    @MockBean
    private FieldConverter fieldConverter;

    @Test
    public void givenSimpleInput_shouldReturnProcessedCar() throws Exception {
        //given
        List<Map<String, String>> input = asList(mapOf("make", "Audi", "model", "A5"));
        given(this.csvHeaders.getMakeHeaders()).willReturn(asList("make"));
        given(this.csvHeaders.getModelHeaders()).willReturn(asList("model"));
        given(this.fieldConverter.convertText(anyString())).willAnswer(returnsFirstArg());

        //when
        List<Car> result = csvContentProcessor.processCsvContent(input);

        //then
        assertThat("should return a car", result, is(notNullValue()));
        assertThat("should return a car", result.size(), is(1));
        assertThat("should parse make of car", result.get(0).getMake(), is("Audi"));
        assertThat("should parse model of car", result.get(0).getModel(), is("A5"));
    }


    @Test
    public void givenInputWithUnknownHeader_shouldProcessAsOtherInformation() throws Exception {
        //given
        List<Map<String, String>> input = asList(mapOf("make", "Audi", "color", "red"));
        given(this.csvHeaders.getMakeHeaders()).willReturn(asList("make"));
        given(this.fieldConverter.convertText(anyString())).willAnswer(returnsFirstArg());

        //when
        List<Car> result = csvContentProcessor.processCsvContent(input);

        //then
        assertThat("should return a car", result, is(notNullValue()));
        assertThat("should return a car", result.size(), is(1));
        assertThat("should parse make of car", result.get(0).getMake(), is("Audi"));
        assertThat("should parse one other information", result.get(0).getOtherInformation(), is(notNullValue()));
        assertThat("should parse one other information", result.get(0).getOtherInformation().size(), is(1));
        assertThat("should parse other information correctly", result.get(0).getOtherInformation().get(0).getName(), is("color"));
        assertThat("should parse other information correctly", result.get(0).getOtherInformation().get(0).getValue(), is("red"));
    }

    @Test
    public void givenInputWithMultipleOptionsFOrSameHeader_shouldParseFirstMatching() throws Exception {
        //given
        List<Map<String, String>> input = asList(mapOf("make", "Audi", "make2", "BMW"));
        given(this.csvHeaders.getMakeHeaders()).willReturn(asList("make", "make1", "make2"));
        given(this.fieldConverter.convertText(anyString())).willAnswer(returnsFirstArg());

        //when
        List<Car> result = csvContentProcessor.processCsvContent(input);

        //then
        assertThat("should return a car", result, is(notNullValue()));
        assertThat("should return a car", result.size(), is(1));
        assertThat("should parse first matching make header", result.get(0).getMake(), is("Audi"));
    }

    private Map<String, String> mapOf(String... values) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < values.length / 2; i++) {
            map.put(values[(i * 2)], values[(i * 2) + 1]);
        }
        return map;
    }

}