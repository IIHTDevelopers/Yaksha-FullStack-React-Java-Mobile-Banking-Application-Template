package com.mobilebanking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobilebanking.dto.TransactionDTO;
import com.mobilebanking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
		// write your logic here
		return null;
	}

	@Override
	public boolean deleteTransactionById(Long id) {
		// write your logic here
		return false;
	}

	@Override
	public List<TransactionDTO> getAllTransactions() {
		// write your logic here
		return null;
	}

	@Override
	public TransactionDTO getTransactionById(Long id) {
		// write your logic here
		return null;
	}

	@Override
	public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
		// write your logic here
		return null;
	}
}