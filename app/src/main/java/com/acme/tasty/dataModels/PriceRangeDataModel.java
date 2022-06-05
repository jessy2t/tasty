package com.acme.tasty.dataModels;

public class PriceRangeDataModel {
    public Integer MinPrice;
    public Integer MaxPrice;

    public PriceRangeDataModel(Integer minPrice, Integer maxPrice) {
        MinPrice = minPrice;
        MaxPrice = maxPrice;
    }
}
