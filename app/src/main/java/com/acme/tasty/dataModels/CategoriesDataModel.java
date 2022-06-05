package com.acme.tasty.dataModels;

import java.util.ArrayList;

public class CategoriesDataModel {
    public Boolean Mexican;
    public Boolean Indian;
    public Boolean Indonesian;
    public Boolean Italian;
    public Boolean German;
    public Boolean American;
    public Boolean Chinese;

    public CategoriesDataModel(Boolean mexican, Boolean indian, Boolean indonesian, Boolean italian, Boolean german,
                               Boolean american, Boolean chinese) {
        Mexican = mexican;
        Indian = indian;
        Indonesian = indonesian;
        Italian = italian;
        German = german;
        American = american;
        Chinese = chinese;
    }

    public ArrayList<String> addCategories(ArrayList list) {
        if(Mexican)
            list.add("Mexikanisch");
        if(Indian)
            list.add("Indisch");
        if(Indonesian)
            list.add("Indonesisch");
        if(Italian)
            list.add("Italienisch");
        if(German)
            list.add("Deutsch");
        if(American)
            list.add("Amerikanisch");
        if(Chinese)
            list.add("Chinesisch");

        return list;
    }
}
