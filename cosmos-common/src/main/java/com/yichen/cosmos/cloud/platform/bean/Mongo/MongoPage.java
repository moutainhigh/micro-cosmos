package com.yichen.cosmos.cloud.platform.bean.Mongo;

import java.util.List;

/**
 * Created by hzsj on 2017/6/26.
 * mongo分页
 */
public class MongoPage {

    private int skip;
    private int limit;
    private String lastId;
    private String lastIdName;
    private List<Object> resultList;
    private int count;
    private int pageSize;
    private int page;
    private int order;
    private String orderColumn;

    public interface ORDER {
        int DESC = -1;
        int ACS = 1;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getLastIdName() {
        return lastIdName;
    }

    public void setLastIdName(String lastIdName) {
        this.lastIdName = lastIdName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.limit = pageSize;
    }

    public int getSkip() {
        this.skip = pageSize * (page - 1);
        return skip;

    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
