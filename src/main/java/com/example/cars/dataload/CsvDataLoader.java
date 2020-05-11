package com.example.cars.dataload;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CsvDataLoader {

    public List<Map<String, String>> loadObjectList(File file) {
        log.info("Loading file: " + file.getName());
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = csvMapper.typedSchemaFor(Map.class).withHeader();

            attachLowerCaseModule(csvMapper);

            MappingIterator<Map<String, String>> it = csvMapper.readerFor(Map.class)
                    .with(csvSchema.withColumnSeparator(','))
                    .readValues(file);

            return it.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + file.getName(), e);
            return Collections.emptyList();
        }
    }

    private void attachLowerCaseModule(CsvMapper csvMapper) {
        SimpleModule module = new SimpleModule("LowerCaseKeyDeserializer",
                Version.unknownVersion());
        module.addKeyDeserializer(Object.class, new LowerCaseKeyDeserializer());
        csvMapper.registerModule(module);
    }
}

