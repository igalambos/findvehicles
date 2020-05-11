package com.example.cars.dataload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties("headers")
public class CsvHeaders {

    private List<String> makeHeaders;
    private List<String> modelHeaders;
    private List<String> termHeaders;
    private List<String> mileageHeaders;
    private List<String> priceHeaders;


}
