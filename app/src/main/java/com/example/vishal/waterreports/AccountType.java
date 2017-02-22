public enum AccountType {
	
	USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Admin");

	private String accountType;

	private AccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}