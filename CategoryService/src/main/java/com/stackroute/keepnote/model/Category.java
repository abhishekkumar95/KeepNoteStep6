package com.stackroute.keepnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Category {

    @Id

    private String id;
    private String categoryName;
    private String categoryDescription;
    private String categoryCreatedBy;
    private Date categoryCreationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryCreatedBy() {
        return categoryCreatedBy;
    }

    public void setCategoryCreatedBy(String categoryCreatedBy) {
        this.categoryCreatedBy = categoryCreatedBy;
    }

    public Date getCategoryCreationDate() {
        return categoryCreationDate;
    }

    public void setCategoryCreationDate(Date categoryCreationDate) {
        this.categoryCreationDate = categoryCreationDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryCreatedBy='" + categoryCreatedBy + '\'' +
                ", categoryCreationDate=" + categoryCreationDate +
                '}';
    }
}
