package com.example.vishal.waterreports;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vishal.waterreports.controller.MapsActivity;
import com.example.vishal.waterreports.model.ConditionOfWater;
import com.example.vishal.waterreports.model.OverallWaterCondition;
import com.example.vishal.waterreports.model.TypeOfWater;
import com.example.vishal.waterreports.model.WaterPurityReport;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AliKhosraviJUnitTest {

    @Rule
    public ActivityTestRule<MapsActivity> mActivityRule = new ActivityTestRule<>(
            MapsActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.vishal.waterreports", appContext.getPackageName());
    }

    @Test
    public void waterPurityTest() {
        WaterPurityReport waterPure = new WaterPurityReport("2/12/16", "0223", 1, "Tim", "Factory"
                , OverallWaterCondition.SAFE, 20, 15);
        assertEquals("2/12/16", waterPure.REPORT_DATE);
        assertEquals("0223", waterPure.REPORT_TIME);
        assertEquals(1, waterPure.REPORT_NUMBER);
        assertEquals("Tim", waterPure.WORKER_NAME);
        assertEquals("Factory", waterPure.LOCATION);
        assertEquals(OverallWaterCondition.SAFE, waterPure.CONDITION);
        assertEquals(20, waterPure.VIRUS);
        assertEquals(15, waterPure.CONTAMINANT);
    }
}