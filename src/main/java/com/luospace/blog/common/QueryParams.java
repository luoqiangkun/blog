package com.luospace.blog.common;

import java.util.HashMap;
import java.util.Map;

public class QueryParams extends HashMap<String,Object> {

    private int pageNum;

    private int pageSize;

    public QueryParams() {
    }

    public QueryParams(Map<String, Object> params) {
        this.putAll(params);

        this.pageNum = Integer.parseInt(params.get("pageNumber").toString());

        this.pageSize = Integer.parseInt(params.get("pageSize").toString());
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryParams{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
