package com.acme.tasty.dataModels;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;

public class OpeningHoursDataModel {
    public String Weekday;
    public String StartTime;
    public String EndTime;

    public OpeningHoursDataModel(String weekday, String startTime, String endTime) {
        Weekday = weekday;
        StartTime = startTime;
        EndTime = endTime;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return StartTime + " - " + EndTime;
    }
}
