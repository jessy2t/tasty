package com.acme.tasty.dataModels;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class RestaurantDataModel {
    public String Name;
    public RestaurantAttributesDataModel Attributes;
    public AddressDataModel Address;
    public String ImageName;

    public RestaurantDataModel(String name, String imageName, RestaurantAttributesDataModel attributes, AddressDataModel address) {
        Name = name;
        ImageName = imageName;
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
