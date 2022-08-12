package com.krypton.citysearchapi.model.response;

import lombok.Data;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class PaginatedApiResponse<T> {
    private T results;
    private Pagination refs;

    public PaginatedApiResponse(T data, String requestPath, int totalPages, int totalRecords, int page, int size, String keyword) {
        this.results = data;
        var links = createPaginatedLinks(requestPath, totalPages, totalRecords, page, size, keyword);
        var currentPage = links.stream().filter(link -> link.getKey() == "self").findFirst().get().getUrl();
        var nextPage = links.stream().filter(link -> link.getKey() == "next").findFirst().get().getUrl();
        this.refs = new Pagination(page, size,
                totalRecords,
                currentPage,
                nextPage
        );

    }

    public List<Link> createPaginatedLinks(String requestPath, int totalPages, int totalRecords, int page, int size, String keyword) {
        if (page >= totalPages)
            page = totalPages;
        if (size > totalRecords)
            size = totalRecords;

        var paginationLinks = new ArrayList<Link>();
        String url = requestPath;
        var obj = url.split("/");
        url = MessageFormat.format("{0}/{1}/{2}", obj[0], obj[1], obj[2]);
        String currentPage = "";
        String nextPage = "";

        if (keyword == null || keyword.isEmpty() || keyword == "") {
            currentPage = MessageFormat.format("{0}?page={1}&size={2}", url, page, size);
            if (totalPages > 1 && (page + 1) <= totalPages) {
                nextPage = MessageFormat.format("{0}?page={1}&size={2}", url, page + 1, size);
            } else {
                nextPage = currentPage;
            }
        } else {
            currentPage = MessageFormat.format("{0}?query={3}&page={1}&size={2}", url, page, size, keyword);
            if (totalPages > 1 && (page + 1) <= totalPages) {
                nextPage = MessageFormat.format("{0}?query={3}&page={1}&size={2}", url, page + 1, size, keyword);
            } else {
                nextPage = currentPage;
            }

        }
        paginationLinks.add(new Link("self", currentPage));
        paginationLinks.add(new Link("next", nextPage));

        return paginationLinks;
    }
}


