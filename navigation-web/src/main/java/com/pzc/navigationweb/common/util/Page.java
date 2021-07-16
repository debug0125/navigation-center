package com.pzc.navigationweb.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryf
 * @date 7/16/21 4:06 PM
 */
public class Page<T> implements Serializable, Iterable<T> {
    protected List<T> result;
    protected int pageSize;
    protected int pageNumber;
    protected int total;
    private int limit;
    private int offset;

    public Page() {
    }

    public Page(int pageNumber, int pageSize, int totalCount) {
        this(pageNumber, pageSize, totalCount, new ArrayList(0));
    }

    public Page(int pageNumber, int pageSize, int total, List<T> result) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("[pageSize] must great than zero");
        } else {
            this.pageSize = pageSize;
            this.pageNumber = PageUtils.computePageNumber(pageNumber, pageSize, total);
            this.total = total;
            this.setResult(result);
        }
    }

    public void setResult(List<T> elements) {
        if (elements == null) {
            throw new IllegalArgumentException("'result' must be not null");
        } else {
            this.result = elements;
        }
    }

    public List<T> getResult() {
        return this.result;
    }

    public boolean isFirstPage() {
        return this.getPageNumber() == 1;
    }

    public boolean isHasPreviousPage() {
        return this.getPageNumber() > 1;
    }

    public int getThisPageFirstElementNumber() {
        return (this.getPageNumber() - 1) * this.getPageSize() + 1;
    }

    public int getNextPageNumber() {
        return this.getPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return this.getPageNumber() - 1;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getFirstResult() {
        return PageUtils.getFirstResult(this.pageNumber, this.pageSize);
    }

    @Override
    public Iterator<T> iterator() {
        return this.result == null ? (Iterator<T>) Collections.emptyList().iterator() : this.result.iterator();
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

