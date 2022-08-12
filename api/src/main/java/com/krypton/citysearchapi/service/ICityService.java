package com.krypton.citysearchapi.service;

import com.krypton.citysearchapi.dto.CityDto;
import com.krypton.citysearchapi.entity.City;
import com.krypton.citysearchapi.model.response.CityResponse;
import com.krypton.citysearchapi.model.response.PaginatedApiResponse;

import java.util.List;

public interface ICityService {
    PaginatedApiResponse<List<CityResponse>> getAllCitiesPaginated(int page, int size, String cityName);

    CityResponse updateCity(int id, CityDto cityObj);

    CityResponse getCityByIdentifier(int id);
}
