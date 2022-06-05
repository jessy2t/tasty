package com.acme.tasty.dataModels;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class RestaurantDataModel {
    public String Name;
    public RestaurantAttributesDataModel Attributes;
    public AddressDataModel Address;

    public RestaurantDataModel(String name, RestaurantAttributesDataModel attributes, AddressDataModel address) {
        Name = name;
        Attributes = attributes;
        Address = address;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return Name;
    }
}
