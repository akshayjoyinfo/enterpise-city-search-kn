package com.krypton.citysearchapi.console;

import com.krypton.citysearchapi.repository.CityRepository;
import com.krypton.citysearchapi.utils.ResourceStaticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ImportCitiesCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ImportCitiesCommandLineRunner.class);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Syncing Cities.csv => Database");

        var cities = ResourceStaticUtils.getCities("cities.csv");
        if (cityRepository.count() <= 0)
            cityRepository.saveAll(cities);

        logger.info("All CSV data successfully imported {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
    }
}
