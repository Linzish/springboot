package me.unc.specification.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于封装一页数据
 */
public class PageData implements Serializable {
    //用于保存当前页码
    private int pageIndex;
    //用于保存满足查询条件下用于分页的数据总量
    private long totalCount;
    //保存当前条件下可以分的总页数
    private int pageSize;
    //保存当前页码查询出的数据总量
    private int pageNum;
    //用于保存查询出来的信息
    private List<Map<String, Object>> stuDatas = new ArrayList<>();

    public PageData() {
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<Map<String, Object>> getStuDatas() {
        return stuDatas;
    }

    public void setStuDatas(List<Map<String, Object>> stuDatas) {
        this.stuDatas = stuDatas;
    }
}
