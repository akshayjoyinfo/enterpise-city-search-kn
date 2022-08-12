package com.krypton.citysearchapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krypton.citysearchapi.dto.CityDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)


public class CityControllerTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getDefaultCitiesWithoutFiltersAndPagination() throws Exception {
        mockMvc.perform(get("/api/v1/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(10))
                .andExpect(jsonPath("$.refs.page").value(1))
                .andExpect(jsonPath("$.refs.size").value(10))
                .andExpect(jsonPath("$.refs.currentPage").value("/api/v1?page=1&size=10"))
                .andExpect(jsonPath("$.refs.nextPage").value("/api/v1?page=2&size=10"));
    }

    @Test
    public void getDefaultCitiesWithPaginationAlone() throws Exception {
        mockMvc.perform(get("/api/v1/cities?page=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(10))
                .andExpect(jsonPath("$.refs.page").value(1))
                .andExpect(jsonPath("$.refs.size").value(10))
                .andExpect(jsonPath("$.refs.currentPage").value("/api/v1?page=1&size=10"))
                .andExpect(jsonPath("$.refs.nextPage").value("/api/v1?page=2&size=10"));
    }


    @Test
    public void getDefaultCitiesWithPaginationParameters() throws Exception {
        mockMvc.perform(get("/api/v1/cities?page=1&size=5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(5))
                .andExpect(jsonPath("$.refs.page").value(1))
                .andExpect(jsonPath("$.refs.size").value(5))
                .andExpect(jsonPath("$.refs.currentPage").value("/api/v1?page=1&size=5"))
                .andExpect(jsonPath("$.refs.nextPage").value("/api/v1?page=2&size=5"));
    }

    @Test
    public void getDefaultCitiesWithPaginationParametersThenSearch() throws Exception {
        mockMvc.perform(get("/api/v1/cities?query=t&page=1&size=10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(8))
                .andExpect(jsonPath("$.refs.page").value(1))
                .andExpect(jsonPath("$.refs.size").value(10))
                .andExpect(jsonPath("$.refs.currentPage").value("/api/v1?query=t&page=1&size=8"))
                .andExpect(jsonPath("$.refs.nextPage").value("/api/v1?query=t&page=1&size=8"));
    }

    @Test
    public void getDefaultCitiesWithNotInBoundPageSize() throws Exception {
        mockMvc.perform(get("/api/v1/cities?page=6&size=10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(0))
                .andExpect(jsonPath("$.refs.currentPage").value("/api/v1?page=6&size=10"))
                .andExpect(jsonPath("$.refs.nextPage").value("/api/v1?page=6&size=10"));
    }

    @Test
    public void getCityByIdentifier() throws Exception {
        mockMvc.perform(get("/api/v1/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ALLOW_EDIT")
    public void updateCityValid() throws Exception {
        mockMvc.perform(put("/api/v1/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CityDto("Gotham", "batman.jpg")))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ALLOW_EDIT")
    public void updateCityInValid() throws Exception {
        mockMvc.perform(put("/api/v1/cities/10008")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CityDto("Gotham", "batman.jpg")))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound());
    }


}