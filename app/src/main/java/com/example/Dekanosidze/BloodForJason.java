package com.example.Dekanosidze;

import com.example.Dekanosidze.room.BloodBank;

import java.util.ArrayList;

public class BloodForJason {
    private ArrayList<BloodBank> bloodBanks;

    public BloodForJason(ArrayList<BloodBank> bloodBanks) {
        this.bloodBanks = bloodBanks;
    }

    public ArrayList<BloodBank> getBloodBanks() {
        return bloodBanks;
    }

    public void setBloodBanks(ArrayList<BloodBank> bloodBanks) {
        this.bloodBanks = bloodBanks;
    }
}
