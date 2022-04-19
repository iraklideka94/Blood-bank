package com.example.Dekanosidze.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BloodBank {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    //Name Flavor exp.date brand weight
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "bloodGroup")
    private String bloodGroup;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "date")
    private int date;

    @ColumnInfo(name = "weight")
    private int weight;

    public BloodBank(String name, String bloodGroup, String phone, String location, int date, int weight) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.location = location;
        this.date = date;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}