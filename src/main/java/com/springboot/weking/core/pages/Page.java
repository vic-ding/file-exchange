package com.springboot.weking.core.pages;

import java.util.List;


public class Page {

    public int status = 200;
    public int size;
    public long total;
    public int page;
    public int pages;
    public List<Object> rows;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> list) {
        this.rows = list;
    }
}
