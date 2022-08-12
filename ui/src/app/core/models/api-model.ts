export interface ApiPaginatedModel<T> {
    results: T;
    refs:    Refs;
}

export interface Refs {
    page:        number;
    size:        number;
    totalCount:  number;
    currentPage: string;
    nextPage:    string;
}

export interface ApiModel<T> {
    results: T;
    status: string;
}

