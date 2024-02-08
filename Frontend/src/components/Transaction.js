import React, { useEffect, useState } from 'react';
import TransactionService from '../services/TransactionService';

const TransactionComponent = () => {
    const [transactions, setTransactions] = useState([]);
    const [transaction, setTransaction] = useState({ amount: '', description: '', username: '', transactionId: '', fullName: '' });
    const [selectedId, setSelectedId] = useState(null);

    useEffect(() => {
        loadTransactions();
    }, []);

    const loadTransactions = () => {
        TransactionService.getAllTransactions().then((data) => {
            setTransactions(data);
        });
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setTransaction({ ...transaction, [name]: value });
    };

    const isCreateButtonDisabled = () => {
        return !transaction.amount || !transaction.description || !transaction.username || !transaction.transactionId || !transaction.fullName;
    };

    const handleCreateTransaction = () => {
        TransactionService.createTransaction(transaction).then(() => {
            loadTransactions();
            setTransaction({ amount: '', description: '', username: '', transactionId: '', fullName: '' });
        });
    };

    const handleUpdateTransaction = () => {
        if (selectedId) {
            TransactionService.updateTransaction(selectedId, transaction).then(() => {
                loadTransactions();
                setTransaction({ amount: '', description: '', username: '', transactionId: '', fullName: '' });
                setSelectedId(null);
            });
        }
    };

    const handleEditTransaction = (id) => {
        TransactionService.getTransactionById(id).then((data) => {
            setTransaction(data);
            setSelectedId(id);
        });
    };

    const handleDeleteTransaction = (id) => {
        TransactionService.deleteTransaction(id).then(() => {
            loadTransactions();
            setTransaction({ amount: '', description: '', username: '', transactionId: '', fullName: '' });
            setSelectedId(null);
        });
    };

    return (
        <div>
            <h1>Transactions</h1>
            <ul>
                {transactions.map((item) => (
                    <li key={item.featureId}>
                        {item.amount} - {item.description} - {item.username} - {item.transactionId} - {item.fullName}
                        <button onClick={() => handleEditTransaction(item.featureId)}>Edit</button>
                        <button onClick={() => handleDeleteTransaction(item.featureId)}>Delete</button>
                    </li>
                ))}
            </ul>
            <div>
                <h2>Create/Update Transaction</h2>
                <input type="number" name="amount" value={transaction.amount} onChange={handleInputChange} placeholder="Amount" />
                <input type="text" name="description" value={transaction.description} onChange={handleInputChange} placeholder="Description" />
                <input type="text" name="username" value={transaction.username} onChange={handleInputChange} placeholder="Username" />
                <input type="text" name="transactionId" value={transaction.transactionId} onChange={handleInputChange} placeholder="Transaction ID" />
                <input type="text" name="fullName" value={transaction.fullName} onChange={handleInputChange} placeholder="Full Name" />
                <button onClick={selectedId ? handleUpdateTransaction : handleCreateTransaction} disabled={isCreateButtonDisabled()}>
                    {selectedId ? 'Update' : 'Create'}
                </button>
            </div>
        </div>
    );
};

export default TransactionComponent;
