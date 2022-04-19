package com.example.Dekanosidze;

import com.example.Dekanosidze.RoomDB.BloodBank;

import java.util.ArrayList;

public class Jason {
    private ArrayList<BloodBank> bloodBanks;

    public Jason(ArrayList<BloodBank> bloodBanks) {
        this.bloodBanks = bloodBanks;
    }

    public ArrayList<BloodBank> getBloodBanks() {
        return bloodBanks;
    }

    public void setBloodBanks(ArrayList<BloodBank> bloodBanks) {
        this.bloodBanks = bloodBanks;
    }
}
