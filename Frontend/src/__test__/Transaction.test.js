import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import TransactionComponent from '../components/Transaction';
import TransactionService from '../services/TransactionService';

jest.mock('../services/TransactionService');

describe('TransactionComponent', () => {
    const mockTransactions = [
        { featureId: '1', amount: 100, description: 'Transaction 1', username: 'user1', transactionId: 't1', fullName: 'User One' },
    ];

    beforeEach(() => {
        TransactionService.getAllTransactions.mockResolvedValue(mockTransactions);
    });

    describe('boundary', () => {
        test('TransactionComponent boundary has Transactions in h1', () => {
            render(<TransactionComponent />);
            const headingElement = screen.getByRole('heading', { name: /transactions/i });
            expect(headingElement).toBeInTheDocument();
        });

        test('TransactionComponent boundary has Create/Update Transaction as h2', () => {
            render(<TransactionComponent />);
            const headingElement = screen.getByRole('heading', { name: /create\/update transaction/i });
            expect(headingElement).toBeInTheDocument();
        });

        test('TransactionComponent boundary has textfield with Amount placeholder', () => {
            render(<TransactionComponent />);
            const amountInput = screen.getByPlaceholderText('Amount');
            expect(amountInput).toBeInTheDocument();
        });

        test('TransactionComponent boundary has textfield with Description placeholder', () => {
            render(<TransactionComponent />);
            const descriptionInput = screen.getByPlaceholderText('Description');
            expect(descriptionInput).toBeInTheDocument();
        });

        test('TransactionComponent boundary has textfield with Username placeholder', () => {
            render(<TransactionComponent />);
            const usernameInput = screen.getByPlaceholderText('Username');
            expect(usernameInput).toBeInTheDocument();
        });

        test('TransactionComponent boundary has textfield with Transaction ID placeholder', () => {
            render(<TransactionComponent />);
            const transactionIdInput = screen.getByPlaceholderText('Transaction ID');
            expect(transactionIdInput).toBeInTheDocument();
        });

        test('TransactionComponent boundary has textfield with Full Name placeholder', () => {
            render(<TransactionComponent />);
            const fullNameInput = screen.getByPlaceholderText('Full Name');
            expect(fullNameInput).toBeInTheDocument();
        });

        test('TransactionComponent boundary has Create button', async () => {
            render(<TransactionComponent />);
            const createButton = await screen.findByRole('button', { name: /create/i });
            expect(createButton).toBeInTheDocument();
        });

        test('TransactionComponent boundary has Edit button', async () => {
            render(<TransactionComponent />);
            const editButton = await screen.findByRole('button', { name: /edit/i });
            expect(editButton).toBeInTheDocument();
        });

        test('TransactionComponent boundary has Delete button', async () => {
            render(<TransactionComponent />);
            const deleteButton = await screen.findByRole('button', { name: /delete/i });
            expect(deleteButton).toBeInTheDocument();
        });
    });
});
