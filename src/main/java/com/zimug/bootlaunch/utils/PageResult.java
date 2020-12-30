package com.zimug.bootlaunch.utils;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author jshe
 * date 2018-06-25 21:08
 */
public class PageResult<T> implements Serializable {

    private List<T> rows;
    private int total;
    private int prePage;
    private int nextPage;
    private int endPage = 1;
    private int pageIndex = 1;
    private int pageSize = 10;

    public List<T> getRows() {
        if (null == rows) {
            rows = Collections.emptyList();
        }
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFirstPage() {
        return 1;
    }

    public int getPrePage() {
        prePage = pageIndex - 1 > 0 ? pageIndex - 1 : 1;
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        nextPage = pageIndex + 1 > endPage ? endPage : pageIndex + 1;
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
