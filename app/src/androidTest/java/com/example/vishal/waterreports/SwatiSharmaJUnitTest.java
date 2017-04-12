package com.example.vishal.waterreports;

import android.support.test.runner.AndroidJUnit4;
import com.example.vishal.waterreports.model.ConditionOfWater;
import com.example.vishal.waterreports.model.WaterSourceReport;
import com.example.vishal.waterreports.model.TypeOfWater;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import android.widget.Button;


/**
 * @author Swati Sharma
 * @version 1.0
 * Tests the WaterSourceReport class
 */

@RunWith(AndroidJUnit4.class)
public class SwatiSharmaJUnitTest {

    @Test
    public void testConditionWater() {
        TypeOfWater currType = TypeOfWater.BOTTLED;
        ConditionOfWater currCond = ConditionOfWater.POTABLE;

        WaterSourceReport newReport = new WaterSourceReport("10/10/10", "11:11", 123,
                "Nemo05", "Atlanta", currType, currCond);
        assertEquals("10/10/10", newReport.REPORT_DATE);
        assertEquals("11:11", newReport.REPORT_TIME);
        assertEquals(123, newReport.REP_NUMBER);
        assertEquals("Nemo05", newReport.REPORTER_NAME);
        assertEquals("Atlanta", newReport.LOCATION);
        assertEquals(currType.getWaterType(), newReport.WATER_TYPE);
        assertEquals(currCond.getWaterCondition(), newReport.WATER_CONDITION);

    }

}
