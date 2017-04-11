package com.example.vishal.waterreports;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vishal.waterreports.controller.MapsActivity;
import com.example.vishal.waterreports.model.TypeOfWater;

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
public class RobertAdamsJUnitTest {

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
    public void determineWaterTypeTest() {
        assertEquals(TypeOfWater.BOTTLED, mActivityRule.getActivity().determineWaterType("BOTTLED"));
        assertEquals(TypeOfWater.SPRING, mActivityRule.getActivity().determineWaterType("SPRING"));
        assertEquals(TypeOfWater.LAKE, mActivityRule.getActivity().determineWaterType("LAKE"));
        assertEquals(TypeOfWater.STREAM, mActivityRule.getActivity().determineWaterType("STREAM"));
        assertEquals(TypeOfWater.WELL, mActivityRule.getActivity().determineWaterType("WELL"));
        assertEquals(TypeOfWater.OTHER, mActivityRule.getActivity().determineWaterType("OTHER"));
    }
}