package com.acme.tasty.dataModels;

public class DietDataModel {
    public Boolean Vegetarian;
    public Boolean Vegan;
    public Boolean Carnivore;
    public Boolean Glutenfree;
    public Boolean Lactosefree;
    public Boolean Fruitarian;

    public DietDataModel(Boolean vegetarian, Boolean vegan, Boolean carnivore, Boolean glutenfree, Boolean lactosefree,
                         Boolean fruitarian) {
        Vegetarian = vegetarian;
        Vegan = vegan;
        Carnivore = carnivore;
        Glutenfree = glutenfree;
        Lactosefree = lactosefree;
        Fruitarian = fruitarian;
    }
}
