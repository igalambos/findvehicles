package com.example.cars.dataload;


import com.example.cars.model.Car;
import com.example.cars.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LoadDataFromFiles implements CommandLineRunner {

    @Value("${data.folder:}")
    private String dataFolderLocation;

    private final CsvDataLoader csvDataLoader;
    private final CsvContentProcessor csvContentProcessor;
    private final CarService carService;


    public LoadDataFromFiles(CsvDataLoader csvDataLoader, CsvContentProcessor csvContentProcessor, CarService carService) {
        this.csvDataLoader = csvDataLoader;
        this.csvContentProcessor = csvContentProcessor;
        this.carService = carService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        File dataFolder = new ClassPathResource(dataFolderLocation).getFile();
        for (final File fileEntry : dataFolder.listFiles()) {
            List<Map<String, String>> filecontent = csvDataLoader.loadObjectList(fileEntry);
            List<Car> cars = csvContentProcessor.processCsvContent(filecontent);
            for (Car car : cars) {
                log.info("Loading to database: " + carService.save(car));
            }

        }

    }
}
