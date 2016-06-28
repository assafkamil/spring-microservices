package com.geekoosh.edu.cloud.vacations.sdk;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableQuery implements Pageable {
    private int pageNumber = 0;
    private int pageSize = 5;
    private int offset = 0;
    private Sort sort;

    public PageableQuery(int pageNumber, int pageSize, int offset, Sort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.offset = offset;
        this.sort = sort;
    }

    public PageableQuery(int pageNumber, int pageSize, int offset) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.offset = offset;
    }

    public PageableQuery(int pageSize, int offset) {
        this.pageSize = pageSize;
        this.offset = offset;
    }

    public PageableQuery(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageableQuery() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public Sort getSort() {
        return sort;
    }

    public Pageable next() {
        return null;
    }

    public Pageable previousOrFirst() {
        return null;
    }

    public Pageable first() {
        return null;
    }

    public boolean hasPrevious() {
        return false;
    }
}
