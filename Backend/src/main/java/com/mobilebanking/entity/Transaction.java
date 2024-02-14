package com.mobilebanking.entity;

public class Transaction {

	private Long featureId;

	private int amount;

	private String description;

	private String username;

	private String transactionId;

	private String fullName;

	public Transaction() {
		super();
	}

	public Transaction(Long featureId, int amount, String description, String username, String transactionId,
			String fullName) {
		super();
		this.featureId = featureId;
		this.amount = amount;
		this.description = description;
		this.username = username;
		this.transactionId = transactionId;
		this.fullName = fullName;
	}

	public Long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "MobileBanking [featureId=" + featureId + ", amount=" + amount + ", description=" + description
				+ ", username=" + username + ", transactionId=" + transactionId + ", fullName=" + fullName + "]";
	}
}
