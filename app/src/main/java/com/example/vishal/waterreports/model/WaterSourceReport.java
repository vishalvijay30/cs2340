package com.example.vishal.waterreports.model;

import java.util.ArrayList;

/**
 * Created by Team 42, CS2430 Spring 2017
 *
 * Information Holder - represents a single Water Source Report in the model
 *
 */

public class WaterSourceReport {

    public final String REPORT_DATE;
    public final String REPORT_TIME;
    public final int REP_NUMBER;
    //public static int REPORT_NUMBER = 0;
    public final String REPORTER_NAME;
    public final String LOCATION;
    public final TypeOfWater WATER_TYPE;
    public final ConditionOfWater WATER_CONDITION;
    public static ArrayList<WaterSourceReport> waterSourceReports
            = new ArrayList<>();

    /**
     * Create a new Water Source Report
     * @param REPORT_DATE       the date the report was submitted (##-##-####)
     * @param REPORT_TIME       the time the report was submitted (24hour: ####)
     * @param REPORTER_NAME     the name of the user that submitted the report
     * @param LOCATION          the location of water that is being reported
     * @param WATER_TYPE        the type of water being reported
     * @param WATER_CONDITION   the condition of the water being reported
     */
    public WaterSourceReport(String REPORT_DATE, String REPORT_TIME, int REP_NUMBER,
                             String REPORTER_NAME,
                             String LOCATION, TypeOfWater WATER_TYPE,
                             ConditionOfWater WATER_CONDITION) {
        this.REPORT_DATE = REPORT_DATE;
        this.REPORT_TIME = REPORT_TIME;
        this.REP_NUMBER = REP_NUMBER;
        this.REPORTER_NAME = REPORTER_NAME;
        this.LOCATION = LOCATION;
        this.WATER_TYPE = WATER_TYPE;
        this.WATER_CONDITION = WATER_CONDITION;
        waterSourceReports.add(this);
    }
    /* Getters for all read only fields */
//    public String getReportDate() {
//        return REPORT_DATE;
//    }
//    public int getReportTime() {
//        return REPORT_TIME;
//    }
//    public int getReportNumber() {
//        return REPORT_NUMBER;
//    }
//    public String getReporterName() {
//        return REPORTER_NAME;
//    }
//    public String getLocation() {
//        return LOCATION;
//    }
//    public TypeOfWater getWaterType() {
//        return WATER_TYPE;
//    }
//    public ConditionOfWater getWaterCondition() {
//        return WATER_CONDITION;
//    }

    /**
     * String representation of the Water Source Report
     * @return the string to be displayed
     */
    @Override
    public String toString() {
        return LOCATION + " has " + WATER_TYPE + " water that is "
                + WATER_CONDITION + " (Report #" + REP_NUMBER
                + " submitted by " + REPORTER_NAME + " at " + REPORT_TIME
                + " hours on " + REPORT_DATE + ")";
    }

}