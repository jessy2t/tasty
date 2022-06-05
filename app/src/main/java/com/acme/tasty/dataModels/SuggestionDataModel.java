package com.acme.tasty.dataModels;

public class SuggestionDataModel {
    public RestaurantDataModel Restaurant;
    public SuggestionBasisDataModel SuggestionBasis;

    public SuggestionDataModel(RestaurantDataModel restaurant, SuggestionBasisDataModel suggestionBasis) {
        Restaurant = restaurant;
        SuggestionBasis = suggestionBasis;
    }
}
