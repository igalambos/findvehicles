package com.example.cars.dataload;

import com.example.cars.exception.FieldFormatException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieldConverterTest {

    private final FieldConverter unitUnderTest = new FieldConverter();

    /**
     * --- convertText ---
     **/

    @Test
    void givenMixedCaseText_shouldConvertToLowercase() throws FieldFormatException {
        //given
        String text = "AsDFgH";

        //when
        String result = unitUnderTest.convertText(text);

        //then
        String expected = "asdfgh";
        assertThat("should be all lowerCase", result, is(equalTo(expected)));
    }

    @Test
    void givenEmptyString_shouldReturnEmptyString() throws FieldFormatException {
        //given
        String text = "";

        //when
        String result = unitUnderTest.convertText(text);

        //then
        String expected = "";
        assertThat("should return empty string", result, is(equalTo(expected)));
    }

    @Test
    void givenNullString_shouldReturnEmptyString() throws FieldFormatException {
        //given
        String text = null;

        //when
        String result = unitUnderTest.convertText(text);

        //then
        String expected = "";
        assertThat("should return empty string", result, is(equalTo(expected)));
    }

    /**
     * --- convertTerm ---
     **/

    @Test
    void givenSimpleNumberTextAsTerm_shouldConvertToLong() throws FieldFormatException {
        //given
        String text = "456789";

        //when
        Long result = unitUnderTest.convertTerm(text);

        //then
        Long expected = 456789L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenFloatNumberTextAsTerm_shouldConvertToLong() throws FieldFormatException {
        //given
        String text = "456789.25";

        //when
        Long result = unitUnderTest.convertTerm(text);

        //then
        Long expected = 456789L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenEmptyStringAsTerm_shouldReturnZero() throws FieldFormatException {
        //given
        String text = "";

        //when
        Long result = unitUnderTest.convertTerm(text);

        //then
        Long expected = 0L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenNullStringAsTerm_shouldReturnZero() throws FieldFormatException {
        //given
        String text = null;

        //when
        Long result = unitUnderTest.convertTerm(text);

        //then
        Long expected = 0L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenTextStringAsTerm_shouldThrowException() {
        //given
        String text = "456gfhdgf";

        //when
        //when
        Exception exception = assertThrows(FieldFormatException.class, () -> {
            unitUnderTest.convertTerm(text);
        });

        //then
        String expectedMessage = "Failed to convert term: 456gfhdgf";
        String actualMessage = exception.getMessage();
        assertThat("should throw an exception", actualMessage, is(equalTo(expectedMessage)));
    }

    /**
     * --- convertPrice ---
     **/

    @Test
    void givenSimpleNumberTextAsPrice_shouldConvertToFloat() throws FieldFormatException {
        //given
        String text = "456789.25";

        //when
        Float result = unitUnderTest.convertPrice(text);

        //then
        Float expected = 456789.25F;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenEmptyStringAsPrice_shouldReturnZero() throws FieldFormatException {
        //given
        String text = "";

        //when
        Float result = unitUnderTest.convertPrice(text);

        //then
        Float expected = 0F;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenNullStringAsPrice_shouldReturnZero() throws FieldFormatException {
        //given
        String text = null;

        //when
        Float result = unitUnderTest.convertPrice(text);

        //then
        Float expected = 0F;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenStringWithCurrencyAsPrice_shouldReturnZero() throws FieldFormatException {
        //given
        String text = "£256.25";

        //when
        Float result = unitUnderTest.convertPrice(text);

        //then
        Float expected = 256.25F;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenTextStringAsPrice_shouldThrowException() {
        //given
        String text = "456gfhdgf";

        //when
        //when
        Exception exception = assertThrows(FieldFormatException.class, () -> {
            unitUnderTest.convertPrice(text);
        });

        //then
        String expectedMessage = "Failed to convert price: 456gfhdgf";
        String actualMessage = exception.getMessage();
        assertThat("should throw an exception", actualMessage, is(equalTo(expectedMessage)));
    }

    /**
     * --- convertMileage ---
     **/

    @Test
    void givenSimpleNumberTextAsMileage_shouldConvertToLong() throws FieldFormatException {
        //given
        String text = "456789";

        //when
        Long result = unitUnderTest.convertMileage(text);

        //then
        Long expected = 456789L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenEmptyStringAsMileage_shouldReturnZero() throws FieldFormatException {
        //given
        String text = "";

        //when
        Long result = unitUnderTest.convertMileage(text);

        //then
        Long expected = 0L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenNullStringAsMileage_shouldReturnZero() throws FieldFormatException {
        //given
        String text = null;

        //when
        Long result = unitUnderTest.convertMileage(text);

        //then
        Long expected = 0L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenFormattedStringAsMileage_shouldReturnZero() throws FieldFormatException {
        //given
        String text = "25k";

        //when
        Long result = unitUnderTest.convertMileage(text);

        //then
        Long expected = 25000L;
        assertThat("should be converted to a number correctly", result, is(equalTo(expected)));
    }

    @Test
    void givenTextStringAsMileage_shouldThrowException() {
        //given
        String text = "456gfhdgf";

        //when
        //when
        Exception exception = assertThrows(FieldFormatException.class, () -> {
            unitUnderTest.convertMileage(text);
        });

        //then
        String expectedMessage = "Failed to convert mileage: 456gfhdgf";
        String actualMessage = exception.getMessage();
        assertThat("should throw an exception", actualMessage, is(equalTo(expectedMessage)));
    }
}