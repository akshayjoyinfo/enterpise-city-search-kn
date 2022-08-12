package com.krypton.citysearchapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    private int page;
    private int size;
    private int totalCount;

    private String currentPage;
    private String nextPage;
}
