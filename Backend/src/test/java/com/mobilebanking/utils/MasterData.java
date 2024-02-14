package com.mobilebanking.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mobilebanking.dto.TransactionDTO;

public class MasterData {

	public static TransactionDTO getTransactionDTO() {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setFeatureId(1L);
		transactionDTO.setAmount(100);
		transactionDTO.setDescription("Sample transaction");
		transactionDTO.setUsername("user123");
		transactionDTO.setTransactionId("TRX123456");
		transactionDTO.setFullName("John Doe");
		return transactionDTO;
	}

	public static List<TransactionDTO> getTransactionDTOList() {
		List<TransactionDTO> transactionDTOList = new ArrayList<>();
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setFeatureId(1L);
		transactionDTO.setAmount(100);
		transactionDTO.setDescription("Sample transaction");
		transactionDTO.setUsername("user123");
		transactionDTO.setTransactionId("TRX123456");
		transactionDTO.setFullName("John Doe");
		transactionDTOList.add(transactionDTO);
		return transactionDTOList;
	}

	public static String asJsonString(Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule()); // Register the module to handle Java 8 date/time types
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
