package com.acme.tasty.dataModels;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class CityDataModel {
    public String ZipCode;
    public String City;

    public CityDataModel(String zipCode, String city) {
        ZipCode = zipCode;
        City = city;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return this.ZipCode + " " + this.City;
    }
}
