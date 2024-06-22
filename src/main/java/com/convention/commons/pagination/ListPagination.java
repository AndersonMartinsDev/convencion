package com.convention.commons.pagination;

import lombok.Getter;

import java.util.Collection;

@Getter
public class ListPagination<T> {
    private Collection<T> content;
    private Long total;
    private Integer page;

    public ListPagination(){}

    public ListPagination(Collection<T> content, Long total, Integer page){
        this.content = content;
        this.total = total;
        this.page = page;
    }
}
