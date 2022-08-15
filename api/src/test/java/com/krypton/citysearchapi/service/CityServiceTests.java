package com.krypton.citysearchapi.service;

import com.krypton.citysearchapi.dto.CityDto;
import com.krypton.citysearchapi.entity.City;
import com.krypton.citysearchapi.model.response.CityResponse;
import com.krypton.citysearchapi.model.response.PaginatedApiResponse;
import com.krypton.citysearchapi.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class )
public class CityServiceTests {

    @Mock
    private CityRepository cityRepository;


    @InjectMocks
    private CityService cityService;

    private City city;

    @BeforeEach
    public void setup(){
        //cityRepository = Mockito.mock(CityRepository.class);
        //cityService = new CityService();
        city = new City(1,"asd","adf");
    }



    // JUnit test for getAllEmployees method
    @Test
    public void getCities(){
        // given - precondition or setup

        City city1 = new City(2,"asd","adf");

        given(cityRepository.getAllPaginatedCities(0,10,"city")).willReturn(new ArrayList<>(Arrays.asList(city, city1)));

        // when -  action or the behaviour that we are going test
        PaginatedApiResponse<List<CityResponse>> cityList = cityService.getAllCitiesPaginated(1,10,"city");

        // then - verify the output
        assertThat(cityList).isNotNull();
        assertThat(cityList.getResults().size()).isEqualTo(2);
    }

    @Test
    public void getCitiesPaginated(){
        City city1 = new City(2,"asd","adf");
        // given - precondition or setup
        given(cityRepository.getAllPaginatedCities(0,1,"city")).willReturn(new ArrayList<>(Arrays.asList(city)));

        // when -  action or the behaviour that we are going test
        PaginatedApiResponse<List<CityResponse>> cityList = cityService.getAllCitiesPaginated(1,1,"city");

        // then - verify the output
        assertThat(cityList).isNotNull();
        assertThat(cityList.getResults().size()).isEqualTo(1);
    }

    @Test
    public void getCity(){
        // given - precondition or setup
        given(cityRepository.findById(1)).willReturn(Optional.of(city));

        // when -  action or the behaviour that we are going test
        CityResponse cityResponse = cityService.getCityByIdentifier(1);

        // then - verify the output
        assertThat(cityResponse).isNotNull();
        assertThat(cityResponse.getId()).isEqualTo(1);
    }

    // JUnit test for getAllEmployees method
    @Test
    public void updateCity(){
        city.setName("BBB");
        city.setPhoto("BBB.jpg");
        given(cityRepository.findById(1)).willReturn(Optional.of(city));
        given(cityRepository.save(city)).willReturn(city);
        CityDto cityDto = new CityDto("BBB", "BBB.jpg");

        // when -  action or the behaviour that we are going test
        CityResponse updatedCity = cityService.updateCity(1,cityDto);

        // then - verify the output
        assertThat(updatedCity.getName()).isEqualTo("BBB");
        assertThat(updatedCity.getPhoto()).isEqualTo("BBB.jpg");
    }
}
