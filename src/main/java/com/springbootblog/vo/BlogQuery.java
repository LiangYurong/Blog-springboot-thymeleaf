package com.springbootblog.vo;

/**
 * 什么作用来的？
 */
public class BlogQuery {
    private String title;
    private Long TypeId;
    private boolean recommend;

    public BlogQuery() {
    }

    public BlogQuery(String title, Long typeId, boolean recommend) {
        this.title = title;
        TypeId = typeId;
        this.recommend = recommend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return TypeId;
    }

    public void setTypeId(Long typeId) {
        TypeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
