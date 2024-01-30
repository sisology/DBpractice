package com.ohgiraffers.model;

public class DepartmentDTO {

    private String id;
    private String title;
    private String locationId;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String id, String title, String locationId) {
        this.id = id;
        this.title = title;
        this.locationId = locationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
