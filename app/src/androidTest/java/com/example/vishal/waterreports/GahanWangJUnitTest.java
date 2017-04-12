package com.example.vishal.waterreports;

import com.example.vishal.waterreports.model.TypeOfWater;
import com.example.vishal.waterreports.controller.MapsActivity;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author Gahan Wang
 * Tests for determing water types
 */
@RunWith(AndroidJUnit4.class)
public class GahanWangJUnitTest {

    @Rule
    public ActivityTestRule<MapsActivity> mapsActivity = new ActivityTestRule<>(
            MapsActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.vishal.waterreports", appContext.getPackageName());
    }
    @Test
    public void determineWaterTypeTest() {
        TypeOfWater bottled = TypeOfWater.BOTTLED;
        assertEquals(bottled, mapsActivity.getActivity().determineWaterType("BOTTLED"));
        TypeOfWater well = TypeOfWater.WELL;
        assertEquals(well, mapsActivity.getActivity().determineWaterType("WELL"));
        TypeOfWater stream = TypeOfWater.STREAM;
        assertEquals(stream, mapsActivity.getActivity().determineWaterType("STREAM"));
        TypeOfWater lake = TypeOfWater.LAKE;
        assertEquals(lake, mapsActivity.getActivity().determineWaterType("LAKE"));
        TypeOfWater spring = TypeOfWater.SPRING;
        assertEquals(spring, mapsActivity.getActivity().determineWaterType("SPRING"));
        TypeOfWater other = TypeOfWater.OTHER;
        assertEquals(other, mapsActivity.getActivity().determineWaterType("OTHER"));
    }

}