package com.mobilebanking.functional;

import static com.mobilebanking.utils.MasterData.asJsonString;
import static com.mobilebanking.utils.MasterData.getTransactionDTO;
import static com.mobilebanking.utils.TestUtils.businessTestFile;
import static com.mobilebanking.utils.TestUtils.currentTest;
import static com.mobilebanking.utils.TestUtils.testReport;
import static com.mobilebanking.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mobilebanking.controller.TransactionController;
import com.mobilebanking.dto.TransactionDTO;
import com.mobilebanking.service.TransactionService;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateTransaction() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();

		when(this.transactionService.createTransaction(any())).thenReturn(transactionDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/transactions")
				.content(asJsonString(transactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(transactionDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetTransactionById() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();

		when(this.transactionService.getTransactionById(any())).thenReturn(transactionDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transactions/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(transactionDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateTransaction() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();
		when(this.transactionService.updateTransaction(any())).thenReturn(transactionDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/transactions/1")
				.content(asJsonString(transactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(transactionDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeleteTransaction() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/transactions/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetAllTransactions() throws Exception {
		TransactionDTO transactionDTO1 = getTransactionDTO();
		TransactionDTO transactionDTO2 = getTransactionDTO();
		when(this.transactionService.getAllTransactions()).thenReturn(List.of(transactionDTO1, transactionDTO2));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transactions")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString()
						.contentEquals(asJsonString(List.of(transactionDTO1, transactionDTO2))) ? "true" : "false"),
				businessTestFile);
	}
}
