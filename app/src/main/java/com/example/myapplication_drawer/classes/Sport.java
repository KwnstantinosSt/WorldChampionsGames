package com.example.myapplication_drawer.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "sport")
public class Sport {

    @PrimaryKey
    @ColumnInfo(name = "sport_id")
    private int id;

    @ColumnInfo (name = "sport_name")
    private String name;

    @ColumnInfo (name = "sport_kind")
    private String kind;

    @ColumnInfo (name = "sport_sex")
    private String sex;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
