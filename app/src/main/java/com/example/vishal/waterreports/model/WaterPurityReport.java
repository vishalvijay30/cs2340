package com.example.vishal.waterreports.model;
import java.util.ArrayList;

/**
 * Created by Team 42, CS2430 Spring 2017
 *
 * Information Holder - represents a single Water Purity Report in the model
 *
 */

public class WaterPurityReport {

    public final String REPORT_DATE;
    public final int REPORT_TIME;
    public final int REPORT_NUMBER;
    public final String WORKER_NAME;
    public final String LOCATION;
    public final OverallWaterCondition CONDITION;
    public final int VIRUS;
    public final int CONTAMINANT;
    public static ArrayList<WaterPurityReport> waterPurityReports
            = new ArrayList<>();

    /**
     * Create a new Water Purity Report
     * @param REPORT_DATE    the date the report was submitted (##-##-####)
     * @param REPORT_TIME    the time the report was submitted (24 hour: ####)
     * @param REPORT_NUMBER  a unique identifying number for the report
     * @param WORKER_NAME    the name of the worker that submitted the report
     * @param LOCATION       the location of water that is being reported
     * @param CONDITION      the overall condition of the water being reported
     * @param VIRUS          the amount of virus parts-per-million in the water
     * @param CONTAMINANT    the amount of contaminant parts-per-million in the water
     */
    public WaterPurityReport(String REPORT_DATE, int REPORT_TIME,
                             int REPORT_NUMBER, String WORKER_NAME,
                             String LOCATION, OverallWaterCondition CONDITION,
                             int VIRUS, int CONTAMINANT) {
        this.REPORT_DATE = REPORT_DATE;
        this.REPORT_TIME = REPORT_TIME;
        this.REPORT_NUMBER = REPORT_NUMBER;
        this.WORKER_NAME = WORKER_NAME;
        this.LOCATION = LOCATION;
        this.CONDITION = CONDITION;
        this.VIRUS = VIRUS;
        this.CONTAMINANT = CONTAMINANT;
        waterPurityReports.add(this);
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
    public String getWorkerName() {
        return WORKER_NAME;
    }
    public String getLocation() {
        return LOCATION;
    }
    public OverallWaterCondition getCondition() {
        return CONDITION;
    }
    public int getVirus() {
        return VIRUS;
    }
    public int getContaminant() {
        return CONTAMINANT;
    }

    /**
     * String representation of the Water Source Report
     * @return the string to be displayed
     */
    @Override
    public String toString() {
        return LOCATION + " has water that is " + CONDITION + " overall with "
                + VIRUS + " virus PPM and " + CONTAMINANT + " contaminant PPM"
                + " (Report #" + REPORT_NUMBER + " submitted by "
                + WORKER_NAME + " at " + REPORT_TIME + " hours on "
                + REPORT_DATE + ")";
    }

}