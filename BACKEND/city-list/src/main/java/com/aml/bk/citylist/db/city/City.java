package com.aml.bk.citylist.db.city;

import javax.persistence.*;

@Entity
@Table(name = "City")
public class City {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String photo;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
