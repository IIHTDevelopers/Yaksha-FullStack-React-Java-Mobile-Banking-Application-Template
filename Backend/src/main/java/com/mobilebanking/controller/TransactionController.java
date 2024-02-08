package com.mobilebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilebanking.dto.TransactionDTO;
import com.mobilebanking.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping
	public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
		TransactionDTO result = transactionService.createTransaction(transactionDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransactionDTO> updateMobileBanking(@PathVariable Long id,
			@RequestBody @Valid TransactionDTO transactionDTO) {
		transactionDTO.setFeatureId(id);
		TransactionDTO existingMobileBanking = transactionService.updateTransaction(transactionDTO);
		return new ResponseEntity<>(existingMobileBanking, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMobileBanking(@PathVariable Long id) {
		boolean result = transactionService.deleteTransactionById(id);
		if (!result) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDTO> getMobileBankingById(@PathVariable Long id) {
		TransactionDTO mobileBanking = transactionService.getTransactionById(id);
		return new ResponseEntity<>(mobileBanking, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<TransactionDTO>> getAllMobileBanking() {
		List<TransactionDTO> mobileBankingList = transactionService.getAllTransactions();
		return new ResponseEntity<>(mobileBankingList, HttpStatus.OK);
	}
}
