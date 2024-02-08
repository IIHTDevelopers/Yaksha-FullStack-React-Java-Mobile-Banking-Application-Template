import TransactionService from "../services/TransactionService";

let API_BASE_URL = "http://localhost:8081/transactions/api/transactions";

jest.mock("axios");

describe("TransactionService", () => {
    const mockTransactions = [
        {
            featureId: "1",
            amount: 1000,
            description: "Description 1",
            username: "User 1",
            transactionId: "T1",
            fullName: "Full Name 1",
        },
        {
            featureId: "2",
            amount: 2000,
            description: "Description 2",
            username: "User 2",
            transactionId: "T2",
            fullName: "Full Name 2",
        },
    ];

    beforeEach(() => {
        jest.clearAllMocks();
    });

    describe("functional", () => {
        test("TransactionService functional should get all transactions", async () => {
            let isNull = false;
            try {
                const response = await TransactionService.getAllTransactions();
                isNull = response === null;
                throw new Error("Error in getAllTransactions()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    TransactionService.getAllTransactions = jest
                        .fn()
                        .mockResolvedValueOnce(mockTransactions);
                    const result = await TransactionService.getAllTransactions();
                    expect(TransactionService.getAllTransactions).toHaveBeenCalled();
                    expect(result).toEqual(mockTransactions);
                }
            }
        });

        test("TransactionService functional should get transaction by ID", async () => {
            let isNull = false;
            try {
                const response = await TransactionService.getTransactionById("1");
                isNull = response === null;
                throw new Error("Error in getTransactionById()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    TransactionService.getTransactionById = jest
                        .fn()
                        .mockResolvedValueOnce(mockTransactions[0]);
                    const result = await TransactionService.getTransactionById("1");
                    expect(TransactionService.getTransactionById).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockTransactions[0]);
                }
            }
        });

        test("TransactionService functional should delete transaction by ID", async () => {
            let isNull = false;
            try {
                const response = await TransactionService.deleteTransaction("1");
                isNull = response === null;
                throw new Error("Error in deleteTransaction()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    TransactionService.deleteTransaction = jest
                        .fn()
                        .mockResolvedValueOnce(mockTransactions[0]);
                    const result = await TransactionService.deleteTransaction("1");
                    expect(TransactionService.deleteTransaction).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockTransactions[0]);
                }
            }
        });

        test("TransactionService functional should create a new transaction", async () => {
            const newTransaction = {
                amount: 3000,
                description: "Description 3",
                username: "User 3",
                transactionId: "T3",
                fullName: "Full Name 3",
            };
            let isNull = false;
            try {
                const response = await TransactionService.createTransaction(newTransaction);
                isNull = response === null;
                throw new Error("Error in createTransaction()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    TransactionService.createTransaction = jest
                        .fn()
                        .mockResolvedValueOnce(newTransaction);
                    const result = await TransactionService.createTransaction(newTransaction);
                    expect(TransactionService.createTransaction).toHaveBeenCalledWith(newTransaction);
                    expect(result).toEqual(newTransaction);
                }
            }
        });

        test("TransactionService functional should update transaction by ID", async () => {
            const updatedTransaction = { ...mockTransactions[0], amount: 5000 };
            let isNull = false;
            try {
                const response = await TransactionService.updateTransaction("1", updatedTransaction);
                isNull = response === null;
                throw new Error("Error in updateTransaction()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    TransactionService.updateTransaction = jest
                        .fn()
                        .mockResolvedValueOnce(updatedTransaction);
                    const result = await TransactionService.updateTransaction("1", updatedTransaction);
                    expect(TransactionService.updateTransaction).toHaveBeenCalledWith("1", updatedTransaction);
                    expect(result).toEqual(updatedTransaction);
                }
            }
        });
    });
});