package com.krypton.citysearchapi.controller;

import com.krypton.citysearchapi.dto.CityDto;
import com.krypton.citysearchapi.entity.City;
import com.krypton.citysearchapi.model.response.ApiResponse;
import com.krypton.citysearchapi.model.response.CityResponse;
import com.krypton.citysearchapi.model.response.PaginatedApiResponse;
import com.krypton.citysearchapi.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cities")

public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping()
    @Operation(description = "Paginated Cities, with page starting from 1-> N, size number of records per page", tags = {"City"},
            summary = "Get All Cities by Pagination")
    public ResponseEntity<PaginatedApiResponse<List<CityResponse>>> getAllCitiesWithPagination(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "page", defaultValue = "1") @Valid @Max(2000) @Min(1) int page,
            @RequestParam(value = "size", defaultValue = "10") @Valid @Max(10) @Min(1) int size
    ) {
        var results = cityService.getAllCitiesPaginated(page, size, query);

        return new ResponseEntity(results, HttpStatus.OK);
    }


    @GetMapping("/{cityId}")
    @Operation(description = "Get City by City Identifier", tags = {"City"},
            summary = "Get City by Identifier")
    public ResponseEntity<ApiResponse<CityResponse>> getCityByIdentifier(@PathVariable int cityId) {
        var result = cityService.getCityByIdentifier(cityId);

        return new ResponseEntity<ApiResponse<CityResponse>>
                (new ApiResponse(HttpStatus.OK.toString(), result), HttpStatus.OK);
    }


    @PutMapping("/{cityId}")
    @Operation(description = "Update City Information using name & photo", tags = {"City"},
            summary = "Update City Information")
    @PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
    public ResponseEntity<ApiResponse<CityResponse>> updateCity(@PathVariable int cityId, @RequestBody CityDto cityDetails
    ) {
        var result = cityService.updateCity(cityId, cityDetails);
        if (result == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApiResponse<CityResponse>>(new ApiResponse(HttpStatus.OK.toString(), result), HttpStatus.OK);
    }
}
