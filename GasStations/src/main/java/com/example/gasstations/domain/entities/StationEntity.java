package com.example.gasstations.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stations")
public class StationEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "street")
    private String street;

    @Column(name = "place")
    private String place;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "dist")
    private Double dist;

    @Column(name = "diesel")
    private Double diesel;

    @Column(name = "e5")
    private Double e5;

    @Column(name = "e10")
    private Double e10;

    @Column(name = "is_open")
    private Boolean isOpen;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "post_code")
    private Integer postCode;


    public StationEntity() {
    }

    public String getName() {
        return name;
    }

    public StationEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public StationEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public StationEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public StationEntity setPlace(String place) {
        this.place = place;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public StationEntity setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public StationEntity setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public Double getDist() {
        return dist;
    }

    public StationEntity setDist(Double dist) {
        this.dist = dist;
        return this;
    }

    public Double getDiesel() {
        return diesel;
    }

    public StationEntity setDiesel(Double diesel) {
        this.diesel = diesel;
        return this;
    }

    public Double getE5() {
        return e5;
    }

    public StationEntity setE5(Double e5) {
        this.e5 = e5;
        return this;
    }

    public Double getE10() {
        return e10;
    }

    public StationEntity setE10(Double e10) {
        this.e10 = e10;
        return this;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public StationEntity setOpen(Boolean open) {
        isOpen = open;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public StationEntity setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public StationEntity setPostCode(Integer postCode) {
        this.postCode = postCode;
        return this;
    }
}
