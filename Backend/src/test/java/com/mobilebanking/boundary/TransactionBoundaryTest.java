package com.mobilebanking.boundary;

import static com.mobilebanking.utils.TestUtils.boundaryTestFile;
import static com.mobilebanking.utils.TestUtils.currentTest;
import static com.mobilebanking.utils.TestUtils.testReport;
import static com.mobilebanking.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mobilebanking.dto.TransactionDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TransactionBoundaryTest {

	private static Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testFeatureNameNotBlank() throws IOException {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(-1);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDescriptionNotBlank() throws IOException {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setDescription(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testUsernameNotBlank() throws IOException {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setUsername(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testFullNameNotBlank() throws IOException {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setFullName(null);
		Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
