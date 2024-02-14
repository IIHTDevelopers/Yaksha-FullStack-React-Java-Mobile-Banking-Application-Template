class Transaction {
    constructor(featureId, amount, description, username, transactionId, fullName) {
        this.featureId = featureId;
        this.amount = amount;
        this.description = description;
        this.username = username;
        this.transactionId = transactionId;
        this.fullName = fullName;
    }
}

export default Transaction;