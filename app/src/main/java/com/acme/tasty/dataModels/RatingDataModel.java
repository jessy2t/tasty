package com.acme.tasty.dataModels;

public class RatingDataModel {
    public Float Rating;
    public String ReviewTitle;
    public String ReviewDescription;
    public String ReviewDate;
    public RestaurantDataModel Restaurant;

    public RatingDataModel (Float rating, String reviewTitle, String reviewDescription, String reviewDate,
                            RestaurantDataModel restaurant) {
        Rating = rating;
        ReviewTitle = reviewTitle;
        ReviewDescription = reviewDescription;
        ReviewDate = reviewDate;
        Restaurant = restaurant;
    }
}
