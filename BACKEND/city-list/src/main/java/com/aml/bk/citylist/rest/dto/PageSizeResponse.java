package com.aml.bk.citylist.rest.dto;

public class PageSizeResponse {

    private int requestedPageSize;
    private long pagesCount;

    public PageSizeResponse(int requestedPageSize, long pagesCount) {
        this.requestedPageSize = requestedPageSize;
        this.pagesCount = pagesCount;
    }

    public int getRequestedPageSize() {
        return requestedPageSize;
    }

    public void setRequestedPageSize(int requestedPageSize) {
        this.requestedPageSize = requestedPageSize;
    }

    public long getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(long pagesCount) {
        this.pagesCount = pagesCount;
    }
}
