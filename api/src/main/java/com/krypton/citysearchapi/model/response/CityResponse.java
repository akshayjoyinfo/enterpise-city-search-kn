package com.krypton.citysearchapi.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krypton.citysearchapi.dto.CityDto;
import lombok.Data;

@Data
public class CityResponse extends CityDto {

    @JsonProperty("_id")
    private int id;

}
