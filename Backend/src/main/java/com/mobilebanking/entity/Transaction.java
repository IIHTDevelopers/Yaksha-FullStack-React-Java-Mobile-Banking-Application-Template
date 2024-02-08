package com.mobilebanking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feature_id")
	private Long featureId;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "description")
	private String description;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "full_name")
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
