package com.example.vishal.waterreports;
/**
 * Created by Team 42, CS2430 Spring 2017
 *
 * Information Holder - represents a single Water Source Report in the model
 *
 */

public class WaterSourceReport {

    public final String REPORT_DATE;
    public final int REPORT_TIME;
    public final int REPORT_NUMBER;
    public final String REPORTER_NAME;
    public final String LOCATION;
    public final TypeOfWater WATER_TYPE;
    public final ConditionOfWater WATER_CONDITION;

    /**
     * Create a new Water Source Report
     * @param REPORT_DATE       the date the report was submitted
     * @param REPORT_TIME       the time the report was submitted
     * @param REPORT_NUMBER     a unique identifying number for the report
     * @param REPORTER_NAME     the name of the user that submitted the report
     * @param LOCATION          the location of water that is being reported
     * @param WATER_TYPE        the type of water being reported
     * @param WATER_CONDITION   the condition of the water being reported
     */
    public WaterSourceReport(String REPORT_DATE, int REPORT_TIME,
                             int REPORT_NUMBER, String REPORTER_NAME,
                             String LOCATION, TypeOfWater WATER_TYPE,
                             ConditionOfWater WATER_CONDITION) {
        this.REPORT_DATE = REPORT_DATE;
        this.REPORT_TIME = REPORT_TIME;
        this.REPORT_NUMBER = REPORT_NUMBER;
        this.REPORTER_NAME = REPORTER_NAME;
        this.LOCATION = LOCATION;
        this.typeOfWater = WATER_TYPE;
        this.conditionOfWater = WATER_CONDITION;
    }

    /* Getters for all read only fields */
    public String getReportDate() {
        return REPORT_DATE;
    }
    public int getReportTime() {
        return REPORT_TIME;
    }
    public int getReportNumber() {
        return REPORT_NUMBER;
    }
    public String getReporterName() {
        return REPORTER_NAME;
    }
    public String getLocation() {
        return LOCATION;
    }
    public TypeOfWater getWaterType() {
        return WATER_TYPE;
    }
    public ConditionOfWater getWaterCondition() {
        return WATER_CONDITION;
    }

    /**
     * String representation of the Water Source Report
     * @return the string to be displayed
     */
    @Override
    public String toString() {
        return REPORTER_NAME + " submitted report number " + REPORT_NUMBER
                + " at " + REPORT_TIME + " on " + REPORT_DATE + ": " + LOCATION
                + " has " + WATER_TYPE + " water that is " + WATER_CONDITION
                + ".";
    }

}