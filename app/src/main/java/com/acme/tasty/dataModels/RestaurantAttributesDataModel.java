package com.acme.tasty.dataModels;

import java.util.ArrayList;

public class RestaurantAttributesDataModel {
    public Boolean HasDeliveryService;
    public Boolean SupportsReservation;
    public Boolean ReservationNecessary;
    public Boolean SupportsInAppPayment;
    public Boolean Vegetarian;
    public Boolean Vegan;
    public CategoriesDataModel Categories;

    public RestaurantAttributesDataModel(Boolean hasDeliveryService, Boolean supportsReservation,
                                         Boolean reservationNecessary, Boolean supportsInAppPayment, Boolean vegetarian,
                                         Boolean vegan, CategoriesDataModel categories) {
        HasDeliveryService = hasDeliveryService;
        SupportsReservation = supportsReservation;
        ReservationNecessary = reservationNecessary;
        SupportsInAppPayment = supportsInAppPayment;
        Vegetarian = vegetarian;
        Vegan = vegan;
        Categories = categories;
    }

    public ArrayList<String> getAttributes() {
        ArrayList<String> result = Categories.addCategories(new ArrayList());

        if(Vegetarian)
            result.add("Vegetarisch");
        if(Vegan)
            result.add("Vegan");
        if(HasDeliveryService)
            result.add("Lieferung");
        if(SupportsReservation)
            result.add("Reservierung");
        if(SupportsInAppPayment)
            result.add("In-App Zahlung");

        return result;
    }
}
