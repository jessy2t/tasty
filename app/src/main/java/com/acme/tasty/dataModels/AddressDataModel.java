package com.acme.tasty.dataModels;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class AddressDataModel {
    public String Street;
    public Integer HouseNumber;
    public CityDataModel City;

    public AddressDataModel(String street, Integer houseNumber, CityDataModel city) {
        Street = street;
        HouseNumber = houseNumber;
        City = city;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return Street + " " + HouseNumber;
    }
}
