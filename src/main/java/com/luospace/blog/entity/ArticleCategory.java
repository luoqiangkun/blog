package com.luospace.blog.entity;

import java.util.Date;

public class ArticleCategory {
    private int categoryId;

    private String categoryName;

    private int categorySort;

    private int categoryEnabled;

    private Date createdAt;

    private Date updatedAt;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

    public int getCategoryEnabled() {
        return categoryEnabled;
    }

    public void setCategoryEnabled(int categoryEnabled) {
        this.categoryEnabled = categoryEnabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categorySort=" + categorySort +
                ", categoryEnabled=" + categoryEnabled +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
