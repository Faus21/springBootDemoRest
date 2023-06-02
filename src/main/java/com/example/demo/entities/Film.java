package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Film")
public class Film{

    @Id
    @Column(name = "IdFilm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFilm;

    @Column(name = "Title")
    private String title;

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
