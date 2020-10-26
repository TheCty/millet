package com.millet.util;

public class page {
    private int pageNow = 1;       //第几页数
    private int pageSize = 12;     //每页显示的条数
    private int totalPage;      //总页数
    private int totalCount;     //总条数

    public int getPageNow() {
        if (pageNow <= 1) {
            return 1;
        }
        if (pageNow >= getTotalPage()) {
            return getTotalPage();
        }
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return getTotalCount() % pageSize == 0 ? getTotalCount() / pageSize : getTotalCount() / pageSize + 1; //0
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}