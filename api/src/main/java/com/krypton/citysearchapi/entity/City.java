package com.krypton.citysearchapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/*
city name is ideal candidate for unique constraint, city can not be duplicated,
when two users are adding data for single city, we can not duplicate, so applying unique constraint it will help
but cities.csv contains duplicates, so skipping that index
 */
@Data
@AllArgsConstructor
@Entity()
@Table(name = "cities", indexes = @Index(name = "ix_city_name", columnList = "name", unique = false))

public class City extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "photo", length = 1000)// url can not be fixed to 255 hence
    private String photo;

    public City() {

    }

    public City(String _name, String _photo) {
        this.name = _name;
        this.photo = _photo;
        this.createdBy = "SYSTEM";
        this.createdDate = new java.sql.Date(System.currentTimeMillis());
    }
}
