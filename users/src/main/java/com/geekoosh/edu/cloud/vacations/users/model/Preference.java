package com.geekoosh.edu.cloud.vacations.users.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Preference {
    @Id @GeneratedValue private Long id;
    private String preferenceType;

    @ManyToMany
    @JoinTable(
            name="preference_user",
            joinColumns={@JoinColumn(name="preference_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private List<User> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(String preferenceType) {
        this.preferenceType = preferenceType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
