package com.example.vishal.waterreports.model;

public enum TypeOfWater {
	
	BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");

	private String waterType;

	private TypeOfWater(String waterType) {
		this.waterType = waterType;
	}

	public String getWaterType() {
		return waterType;
	}

	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}
}