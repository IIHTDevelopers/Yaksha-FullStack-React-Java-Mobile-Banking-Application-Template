package com.mobilebanking.exception;

import static com.mobilebanking.utils.MasterData.getTransactionDTO;
import static com.mobilebanking.utils.TestUtils.currentTest;
import static com.mobilebanking.utils.TestUtils.exceptionTestFile;
import static com.mobilebanking.utils.TestUtils.testReport;
import static com.mobilebanking.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mobilebanking.dto.TransactionDTO;
import com.mobilebanking.service.TransactionService;
import com.mobilebanking.service.impl.TransactionServiceImpl;
import com.mobilebanking.utils.MasterData;

@WebMvcTest(TransactionServiceImpl.class)
@AutoConfigureMockMvc
public class TransactionExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateTransactionInvalidDataException() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();
		transactionDTO.setFullName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/transactions")
				.content(MasterData.asJsonString(transactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404, exceptionTestFile);
	}

	@Test
	public void testUpdateTransactionInvalidDataException() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();
		transactionDTO.setUsername(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/transactions/" + transactionDTO.getFeatureId())
				.content(MasterData.asJsonString(transactionDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404, exceptionTestFile);
	}

	@Test
	public void testGetTransactionByIdResourceNotFoundException() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Mobile Banking not found");

		when(this.transactionService.getTransactionById(transactionDTO.getFeatureId()))
				.thenThrow(new ResourceNotFoundException("Mobile Banking not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transactions/" + transactionDTO.getFeatureId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404, exceptionTestFile);
	}

	@Test
	public void testDeleteTransactionByIdResourceNotFoundException() throws Exception {
		TransactionDTO transactionDTO = getTransactionDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Mobile Banking not found");

		when(this.transactionService.deleteTransactionById(transactionDTO.getFeatureId()))
				.thenThrow(new ResourceNotFoundException("Mobile Banking not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/api/transactions/" + transactionDTO.getFeatureId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404, exceptionTestFile);
	}
}
