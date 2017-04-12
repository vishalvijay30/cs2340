package com.example.vishal.waterreports.controller;

import com.example.vishal.waterreports.model.TypeOfWater;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

/**
 * @author Gahan Wang
 * Tests for determing water types
 */
public class MapsActivityTest {
    private MapsActivity mapsActivity;
    @Before
    public void setUp() {
        mapsActivity = new MapsActivity();
    }
    @Test
    public void determineWaterTypeTest() {
        String bottled = new TypeOfWater.BOTTLED;
        assertEquals(bottled, mapsActivity.determineWaterType("BOTTLED"));
        String well = new TypeOfWater.WELL;
        assertEquals(well, mapsActivity.determineWaterType("WELL"));
        String stream = new TypeOfWater().STREAM;
        assertEquals(stream, mapsActivity.determineWaterType("STREAM"));
        String lake = new TypeOfWater.LAKE;
        assertEquals(lake, mapsActivity.determineWaterType("LAKE"));
        String spring = new TypeOfWater.SPRING;
        assertEquals(spring, mapsActivity.determineWaterType("SPRING"));
        String other = new TypeOfWater().OTHER;
        assertEquals(other, mapsActivity.determineWaterType("OTHER"));
    }

}