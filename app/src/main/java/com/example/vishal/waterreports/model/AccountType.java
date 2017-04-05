package com.example.vishal.waterreports.model;

public enum AccountType {
	
	USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Admin");

	private String accountType;

	AccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}