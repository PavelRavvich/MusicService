package ru.pravvich.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by pavel on 03.07.17.
 */
public class Client {

    private int id;

    private String name;

    private String login;

    private String password;

    private Address address;

    private List<String> musics;

    private Timestamp create;

    private String role;

    private int roleId;

    private String email;


    public Client() {
    }


    public Client(int id,
                  String name,
                  String login,
                  String password,
                  Address address,
                  Timestamp create,
                  int roleId,
                  String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.create = create;
        this.roleId = roleId;
        this.email = email;
    }

    public Client(final String name,
                  final String login,
                  final String password,
                  final Address address,
                  final List<String> musics,
                  final Timestamp create,
                  final String role,
                  final String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.musics = musics;
        this.create = create;
        this.role = role;
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        if (roleId != 1 && roleId != 2 && roleId != 3) {
            throw new IllegalArgumentException("Invalid role.");
        }

        this.roleId = roleId;
        if (roleId == 1) role = "user";
        if (roleId == 2) role = "moderator";
        if (roleId == 3) role = "admin";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (roleId != client.roleId) return false;
        if (!name.equals(client.name)) return false;
        if (!login.equals(client.login)) return false;
        if (!password.equals(client.password)) return false;
        return email.equals(client.email);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + roleId;
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", email='" + email + '\'' +
                '}';
    }
}
