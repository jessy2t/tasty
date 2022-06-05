package com.acme.tasty.dataModels;

public class SuggestionBasisDataModel {
    public String DeliveryOrReservation;
    public String FoodOrRestaurantSuggestion;
    public String DietPreference;
    public Integer MinPrice;
    public Integer MaxPrice;
    public CategoriesDataModel Categories;
    public DietDataModel FixedDiet;
    public PriceRangeDataModel FixedPriceRange;

    public SuggestionBasisDataModel(String deliveryOrReserveration, String foodOrRestaurantSuggestion,
                                    String dietPreference, Integer minPrice, Integer maxPrice,
                                    CategoriesDataModel categories, DietDataModel fixedDiet,
                                    PriceRangeDataModel fixedPriceRange) {
        DeliveryOrReservation = deliveryOrReserveration;
        FoodOrRestaurantSuggestion = foodOrRestaurantSuggestion;
        DietPreference = dietPreference;
        MinPrice = minPrice;
        MaxPrice = maxPrice;
        Categories = categories;
        FixedDiet = fixedDiet;
        FixedPriceRange = fixedPriceRange;
    }
}
