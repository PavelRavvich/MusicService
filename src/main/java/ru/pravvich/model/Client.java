package ru.pravvich.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by pavel on 03.07.17.
 */
public class Client {

    private Address address;

    private List<String> musics;

    private Timestamp create;

    private String role;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getMusics() {
        return musics;
    }

    public void setMusics(List<String> musics) {
        this.musics = musics;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
