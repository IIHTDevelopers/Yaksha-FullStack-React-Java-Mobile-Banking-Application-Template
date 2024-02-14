package com.mobilebanking.service;

import java.util.List;

import com.mobilebanking.dto.TransactionDTO;

public interface TransactionService {
	TransactionDTO createTransaction(TransactionDTO transactionDTO);

	boolean deleteTransactionById(Long id);

	List<TransactionDTO> getAllTransactions();

	TransactionDTO getTransactionById(Long id);

	TransactionDTO updateTransaction(TransactionDTO transactionDTO);
}
