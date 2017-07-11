package com.chusu.platform.util;

import com.chusu.apps.config.Constants;

public class PageUtil {

    private Integer pageNo;

    private Integer pageSize;

    private Integer beginIndex;

    private Integer endIndex;

    public PageUtil() {

    }

    public PageUtil(Integer pageNo, Integer pageSize) {
        pageNo = (pageNo == null || pageNo <= 1) ? Constants.page.PAGE_NO : pageNo;
        pageSize = (pageSize == null) ? Constants.page.PAGE_SIZE : pageSize;

        this.beginIndex = pageNo * pageSize;
        this.endIndex = pageNo * pageSize + pageSize;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
