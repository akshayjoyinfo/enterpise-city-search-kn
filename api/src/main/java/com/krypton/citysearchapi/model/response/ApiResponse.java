package com.krypton.citysearchapi.model.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String status;
    private T results;


    public ApiResponse(String status, T data) {
        this.status = status;
        this.results = data;
    }
}
