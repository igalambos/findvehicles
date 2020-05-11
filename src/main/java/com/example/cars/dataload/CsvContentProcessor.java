package com.example.cars.dataload;

import com.example.cars.exception.FieldFormatException;
import com.example.cars.model.Car;
import com.example.cars.model.OtherInformation;
import com.example.cars.model.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
@Component
public class CsvContentProcessor {

    private final CsvHeaders csvHeaders;
    private final FieldConverter fieldConverter;

    public CsvContentProcessor(CsvHeaders csvHeaders, FieldConverter fieldConverter) {
        this.csvHeaders = csvHeaders;
        this.fieldConverter = fieldConverter;
    }

    public List<Car> processCsvContent(List<Map<String, String>> content) {
        List<Car> result = new ArrayList<>();

        for (Map<String, String> row : content) {
            log.info("parsing row with data: " + row);

            try {
                Car car = new Car();

                parseMake(row, car);
                parseModel(row, car);
                parseMileage(row, car);
                parseTerm(row, car);
                parsePrice(row, car);
                parseOtherInformation(row, car);

                result.add(car);
                log.info("Car created from row: " + car);
            } catch (FieldFormatException e) {
                log.warn("Failed to parse row " + row, e);
            }
        }
        return result;
    }

    private void parseOtherInformation(Map<String, String> row, Car car) throws FieldFormatException {
        //everything not matching predefined headers should be saved as other information
        for (Entry<String, String> entry : row.entrySet()) {
            car.addOtherInformation(new OtherInformation(entry.getKey(), fieldConverter.convertText(entry.getValue())));
        }
    }

    private void parsePrice(Map<String, String> row, Car car) throws FieldFormatException {
        for (String header : csvHeaders.getPriceHeaders()) {
            if (row.containsKey(header)) {
                car.addPrice(new Price(fieldConverter.convertPrice(row.get(header))));
                row.remove(header);
                break;
            }
        }
    }

    private void parseTerm(Map<String, String> row, Car car) throws FieldFormatException {
        for (String header : csvHeaders.getTermHeaders()) {
            if (row.containsKey(header)) {
                car.setTerm(fieldConverter.convertTerm(row.get(header)));
                row.remove(header);
                break;
            }
        }
    }

    private void parseMileage(Map<String, String> row, Car car) throws FieldFormatException {
        for (String header : csvHeaders.getMileageHeaders()) {
            if (row.containsKey(header)) {
                car.setMileage(fieldConverter.convertMileage(row.get(header)));
                row.remove(header);
                break;
            }
        }
    }

    private void parseModel(Map<String, String> row, Car car) throws FieldFormatException {
        for (String header : csvHeaders.getModelHeaders()) {
            if (row.containsKey(header)) {
                car.setModel(fieldConverter.convertText(row.get(header)));
                row.remove(header);
                break;
            }
        }
    }

    private void parseMake(Map<String, String> row, Car car) throws FieldFormatException {
        for (String header : csvHeaders.getMakeHeaders()) {
            if (row.containsKey(header)) {
                car.setMake(fieldConverter.convertText(row.get(header)));
                row.remove(header);
                break;
            }
        }
    }
}


