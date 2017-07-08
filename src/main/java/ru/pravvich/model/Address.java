package ru.pravvich.model;

/**
 * Created by pavel on 04.07.17.
 */
public class Address {
    private String country;
    private String city;
    private int id;

    public Address() {
    }

    public Address(int id) {
        this.id = id;
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public Address(String country, String city, int id) {
        this.country = country;
        this.city = city;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
