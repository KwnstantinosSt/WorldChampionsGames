package com.example.myapplication_drawer.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "team",
        foreignKeys = {
        @ForeignKey(entity = Sport.class,
                parentColumns = "sport_id",
                childColumns = "team_sport_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)})
public class Team {

    @PrimaryKey
    @ColumnInfo(name = "team_id")
    private int id;

    @ColumnInfo (name = "team_name")
    private String name;

    @ColumnInfo (name = "team_field_name")
    private String field_name;

    @ColumnInfo (name = "team_town")
    private String town;

    @ColumnInfo (name = "team_country")
    private String country;

    @ColumnInfo (name = "team_sport_id")
    private int sport_id;

    @ColumnInfo (name = "team_date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSport_id() {
        return sport_id;
    }

    public void setSport_id(int sport_id) {
        this.sport_id = sport_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
