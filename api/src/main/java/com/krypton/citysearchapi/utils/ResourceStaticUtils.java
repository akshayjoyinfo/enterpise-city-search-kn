package com.krypton.citysearchapi.utils;

import com.krypton.citysearchapi.console.ImportCitiesCommandLineRunner;
import com.krypton.citysearchapi.dto.CityDto;
import com.krypton.citysearchapi.entity.City;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceStaticUtils {
    private static final Logger logger = LoggerFactory.getLogger(ImportCitiesCommandLineRunner.class);

    public static List<City> getCities(String resourceName) throws IOException {
        Resource resource = new ClassPathResource("static/" + resourceName);
        List<City> results = new ArrayList<City>();

        CsvToBean csv = new CsvToBean();

        String csvFilename = resource.getFile().getAbsolutePath();
        logger.info("fileName {}", csvFilename);
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilename)).withSkipLines(1).build();

        //Set column mapping strategy
        List list = csv.parse(setColumMapping(), csvReader);

        for (Object object : list) {
            CityDto city = (CityDto) object;
            results.add(new City(city.getName(), city.getPhoto()));
        }

        return results;
    }

    private static ColumnPositionMappingStrategy setColumMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(CityDto.class);

        String[] columns = new String[]{"name", "photo"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
