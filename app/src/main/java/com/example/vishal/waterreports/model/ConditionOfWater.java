package com.example.vishal.waterreports.model;
public enum ConditionOfWater {
	
	WASTE("Waste"), TREATABLECLEAR("Treatable-Clear"), TREATABLEMUDDY("Treatable-Muddy"), POTABLE("Potable");

	private String waterCondition;

	ConditionOfWater(String waterCondition) {
		this.waterCondition = waterCondition;
	}

	public String getWaterCondition() {
		return waterCondition;
	}

	public void setWaterCondition(String waterCondition) {
		this.waterCondition = waterCondition;
	}
}