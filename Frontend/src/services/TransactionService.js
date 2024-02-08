const API_BASE_URL = 'http://localhost:8081/transactions/api/transactions';

const getAllTransactions = async () => {
    const response = await fetch(API_BASE_URL);
    return response.json();
};

const getTransactionById = async (id) => {
    const response = await fetch(`${API_BASE_URL}/${id}`);
    return response.json();
};

const createTransaction = async (transaction) => {
    const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(transaction),
    });
    return response.json();
};

const updateTransaction = async (id, transaction) => {
    const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(transaction),
    });
    return response.json();
};

const deleteTransaction = async (id) => {
    await fetch(`${API_BASE_URL}/${id}`, {
        method: 'DELETE',
    });
};

export default {
    getAllTransactions,
    getTransactionById,
    createTransaction,
    updateTransaction,
    deleteTransaction,
};
