package br.com.dev.model;

import org.springframework.data.domain.Sort;

public class StudentPage {
    private int page= 0;
    private int size = 3;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sort = "name";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
