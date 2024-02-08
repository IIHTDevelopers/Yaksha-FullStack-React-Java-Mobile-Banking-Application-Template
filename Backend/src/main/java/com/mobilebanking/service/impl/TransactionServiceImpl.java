package com.mobilebanking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilebanking.dto.TransactionDTO;
import com.mobilebanking.entity.Transaction;
import com.mobilebanking.exception.ResourceNotFoundException;
import com.mobilebanking.repo.TransactionRepository;
import com.mobilebanking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper) {
		this.transactionRepository = transactionRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
		Transaction savedMobileBanking = transactionRepository.save(transaction);
		return modelMapper.map(savedMobileBanking, TransactionDTO.class);
	}

	@Override
	public boolean deleteTransactionById(Long id) {
		if (transactionRepository.existsById(id)) {
			transactionRepository.deleteById(id);
			return true;
		}
		throw new ResourceNotFoundException("Mobile Banking not found");
	}

	@Override
	public List<TransactionDTO> getAllTransactions() {
		List<Transaction> mobileBankingList = transactionRepository.findAll();
		return mobileBankingList.stream().map(mobileBanking -> modelMapper.map(mobileBanking, TransactionDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public TransactionDTO getTransactionById(Long id) {
		return transactionRepository.findById(id)
				.map(mobileBanking -> modelMapper.map(mobileBanking, TransactionDTO.class))
				.orElseThrow(() -> new ResourceNotFoundException("Mobile Banking not found"));
	}

	@Override
	public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
		Long mobileBankingId = transactionDTO.getFeatureId();
		if (transactionRepository.existsById(mobileBankingId)) {
			Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
			Transaction updatedMobileBanking = transactionRepository.save(transaction);
			return modelMapper.map(updatedMobileBanking, TransactionDTO.class);
		}
		throw new ResourceNotFoundException("Mobile Banking not found");
	}
}
