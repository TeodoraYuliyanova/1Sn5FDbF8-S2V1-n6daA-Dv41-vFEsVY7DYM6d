package com.example.gasstations.domain.dtos.model;

public class StationDTO {

    private String name;

    private String brand;

    private String street;

    private String place;

    private Double lat;

    private Double lng;

    private Double dist;

    private Double diesel;

    private Double e5;

    private Double e10;

    private Boolean isOpen;

    private String houseNumber;

    private Integer postCode;

    public StationDTO() {
    }

    public String getName() {
        return name;
    }

    public StationDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public StationDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public StationDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public StationDTO setPlace(String place) {
        this.place = place;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public StationDTO setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public StationDTO setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public Double getDist() {
        return dist;
    }

    public StationDTO setDist(Double dist) {
        this.dist = dist;
        return this;
    }

    public Double getDiesel() {
        return diesel;
    }

    public StationDTO setDiesel(Double diesel) {
        this.diesel = diesel;
        return this;
    }

    public Double getE5() {
        return e5;
    }

    public StationDTO setE5(Double e5) {
        this.e5 = e5;
        return this;
    }

    public Double getE10() {
        return e10;
    }

    public StationDTO setE10(Double e10) {
        this.e10 = e10;
        return this;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public StationDTO setOpen(Boolean open) {
        isOpen = open;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public StationDTO setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public StationDTO setPostCode(Integer postCode) {
        this.postCode = postCode;
        return this;
    }
}
