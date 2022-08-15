package com.krypton.citysearchapi.service;

import antlr.StringUtils;
import com.krypton.citysearchapi.dto.CityDto;
import com.krypton.citysearchapi.entity.City;
import com.krypton.citysearchapi.model.response.CityResponse;
import com.krypton.citysearchapi.model.response.PaginatedApiResponse;
import com.krypton.citysearchapi.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService {


    private CityRepository cityRepository;

    public CityService(CityRepository _cityRepository) {
        this.cityRepository = _cityRepository;
    }



    @Override
    public PaginatedApiResponse<List<CityResponse>> getAllCitiesPaginated(int page, int size, String cityName) {
        var cityEntities = new ArrayList<City>();
        var cityCounts = 0;
        var calculatedPage = page - 1;
        int currentPage = calculatedPage * size;

        if ((cityName == null) || (cityName.isEmpty() == true))
            cityName = "";

        cityEntities = (ArrayList<City>) cityRepository.getAllPaginatedCities(currentPage, size, cityName);
        cityCounts = cityRepository.getAllCounts(cityName);
        var totalPages = (int) Math.ceil(cityCounts / size) + 1;

        System.out.println(totalPages);
        System.out.println(cityCounts);

        var paginatedResponse = new PaginatedApiResponse(getCityResponseList(cityEntities),
                "/api/v1/cities",
                totalPages,
                cityCounts,
                page,
                size,
                cityName
        );
        return paginatedResponse;
    }

    @Override
    public CityResponse updateCity(int id, CityDto cityObj) {
        Optional<City> currentCity = cityRepository.findById(id);
        return currentCity.map(city -> {
            city.setName(cityObj.getName());
            city.setPhoto(cityObj.getPhoto());
            return cityRepository.save(city);
        }).map(this::getCityResponse).orElse(null);
    }

    @Override
    public CityResponse getCityByIdentifier(int id) {
        Optional<City> currentCity = cityRepository.findById(id);
        return currentCity.map(this::getCityResponse).orElse(null);
    }


    private List<CityResponse> getCityResponseList(List<City> cityEntities) {
        List<CityResponse> results = new ArrayList<CityResponse>();

        cityEntities.forEach(ce -> {
            results.add(getCityResponse(ce));
        });
        return results;
    }

    private CityResponse getCityResponse(City cityEntity) {
        var re = new CityResponse();
        re.setId(cityEntity.getId());
        re.setName(cityEntity.getName());
        re.setPhoto(cityEntity.getPhoto());

        return re;
    }
}
