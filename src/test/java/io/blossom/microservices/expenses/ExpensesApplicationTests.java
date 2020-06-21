/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.blossom.microservices.expenses.domain.model.Expense;
import io.blossom.microservices.expenses.domain.model.ExpenseUpdate;
import io.blossom.microservices.expenses.domain.model.LinkedAccount;
import io.blossom.microservices.expenses.domain.model.LinkedTransactions;
import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.request.DeleteExpensesRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpensesBatchRequestModel;
import io.blossom.microservices.expenses.domain.model.request.UpdateExpensesRequestModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest
@AutoConfigureMockMvc
class ExpensesApplicationTests {

	private static String expenseId1;
	private static String expenseId2;
	private static String expenseId3;
	List<Expense> expenses;
	@Autowired
	private MockMvc mockMvc;
	private AddExpenseRequestModel addExpenseRequestModel;
	private Expense expense;
	private ExpenseUpdate expenseUpdate;
	private UpdateExpensesRequestModel updateExpensesRequestModel;
	private DeleteExpensesRequestModel deleteExpensesRequestModel;
	private ObjectMapper om = new ObjectMapper();

	public static String getExpenseId1() {
		return expenseId1;
	}

	public static void setExpenseId1(String expenseId1) {
		ExpensesApplicationTests.expenseId1 = expenseId1;
	}

	public static String getExpenseId2() {
		return expenseId2;
	}

	public static void setExpenseId2(String expenseId2) {
		ExpensesApplicationTests.expenseId2 = expenseId2;
	}

	public static String getExpenseId3() {
		return expenseId3;
	}

	public static void setExpenseId3(String expenseId3) {
		ExpensesApplicationTests.expenseId3 = expenseId3;
	}

	@BeforeEach
	void setUp() {
		addExpenseRequestModel = new AddExpenseRequestModel();
		addExpenseRequestModel.setUsername("sbin4life");
		addExpenseRequestModel.setName("abcdefghijk");
		addExpenseRequestModel.setAmount(21.34d);
		addExpenseRequestModel.setMonth(LocalDate.of(2020, 4, 1));
		addExpenseRequestModel.setReoccurring(true);

		expense = new Expense();
		expense.setUsername("sbin4life");
		expense.setName("kjihgfedcba");
		expense.setAmount(21.34d);
		expense.setMonth(LocalDate.of(2020, 3, 1));
		expense.setReoccurring(false);
		expenses = Arrays.asList(expense, expense);

		expenseUpdate = new ExpenseUpdate();
		expenseUpdate.setLinkedAccount(new LinkedAccount("1", "e"));
		expenseUpdate.setLinkedTransactions(Collections.singletonList(new LinkedTransactions("sd", 1d)));
	}

	@Test
	void ATestAddExpense() throws Exception {
		MvcResult result = mockMvc.perform(post("/expenses/api/v1")
				.content(om.writeValueAsString(addExpenseRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String string = result.getResponse().getContentAsString();
		Expense rm = om.readValue(string, Expense.class);
		setExpenseId1(rm.getId());
	}


	@Test
	void testAddExpenseNoUsername() throws Exception {
		addExpenseRequestModel.setUsername(null);
		mockMvc.perform(post("/expenses/api/v1")
				.content(om.writeValueAsString(addExpenseRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param username is missing."));
	}

	@Test
	void testAddExpenseNoName() throws Exception {
		addExpenseRequestModel.setName(null);
		mockMvc.perform(post("/expenses/api/v1")
				.content(om.writeValueAsString(addExpenseRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param name is missing."));
	}

	@Test
	void testAddExpenseNoAmount() throws Exception {
		addExpenseRequestModel.setAmount(null);
		mockMvc.perform(post("/expenses/api/v1")
				.content(om.writeValueAsString(addExpenseRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param amount is missing."));
		;
	}

	@Test
	void testAddExpenseNoMonth() throws Exception {
		addExpenseRequestModel.setMonth(null);
		mockMvc.perform(post("/expenses/api/v1")
				.content(om.writeValueAsString(addExpenseRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param month is missing."));
	}

	@Test
	void BTestBatchAddExpense() throws Exception {
		MvcResult result = mockMvc.perform(post("/expenses/api/v1/batch")
				.content(om.writeValueAsString(new ExpensesBatchRequestModel(expenses)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		String string = result.getResponse().getContentAsString();
		ExpenseListResponseModel rm = om.readValue(string, ExpenseListResponseModel.class);
		setExpenseId2(rm.getExpenses().get(0).getId());
		setExpenseId3(rm.getExpenses().get(1).getId());
	}

	@Test
	void testBatchAddExpenseNoArray() throws Exception {
		mockMvc.perform(post("/expenses/api/v1/batch")
				.content(om.writeValueAsString(new ExpensesBatchRequestModel(null)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenses is missing."));
	}

	@Test
	void CTestQueryExpenseName1() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4life")
				.param("name", "abcdefghijk")
				.param("months", "2020-04-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses").isNotEmpty());
	}

	@Test
	void DTestQueryExpenseName2() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4life")
				.param("name", "kjihgfedcba")
				.param("months", "2020-03-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses").isNotEmpty());
	}

	@Test
	void ETestQueryExpenseMonth1() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4life")
				.param("months", "2020-03-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses").isNotEmpty());
	}

	@Test
	void FTestQueryExpenseMonth2() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4life")
				.param("months", "2020-04-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses").isNotEmpty());
	}

	@Test
	void GTestQueryExpenseMonth3() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4life")
				.param("months", "2020-03-01")
				.param("months", "2020-04-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses").isNotEmpty());
	}

	@Test
	void HtestQueryExpensesNoResults() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4lifesadf")
				.param("months", "2020-03-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No expenses found with the given search criteria"));
	}

	@Test
	void ItestQueryExpensesNoResults2() throws Exception {
		mockMvc.perform(get("/expenses/api/v1/sbin4life")
				.param("name", "abcdefghijk")
				.param("months", "2020-02-01")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No expenses found with the given search criteria"));
	}

	@Test
	void JTestUpdateExpense() throws Exception {
		expenseUpdate.setExpenseId(getExpenseId1());
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void testUpdateExpenseNoId() throws Exception {
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseUpdates[0].expenseId is missing."));

	}

	@Test
	void testUpdateExpenseNoTransactionId() throws Exception {
		expenseUpdate.setExpenseId("test");
		expenseUpdate.setLinkedTransactions(Collections.singletonList(new LinkedTransactions(null, 1d)));
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseUpdates[0].linkedTransactions[0].transactionId is missing."));

	}

	@Test
	void testUpdateExpenseNoAmount() throws Exception {
		expenseUpdate.setExpenseId("test");
		expenseUpdate.setLinkedTransactions(Collections.singletonList(new LinkedTransactions("null", null)));
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseUpdates[0].linkedTransactions[0].amount is missing."));

	}

	@Test
	void testUpdateExpenseNotSuccessful() throws Exception {
		expenseUpdate.setExpenseId("1625sa");
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses[0].alterSuccessful").value(false));

	}

	@Test
	void testUpdateExpenseNoLinkedAccountId() throws Exception {
		expenseUpdate.setExpenseId("test");
		expenseUpdate.setLinkedAccount(new LinkedAccount(null, "testing"));
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseUpdates[0].linkedAccount.accountId is missing."));

	}

	@Test
	void testUpdateExpenseNoLinkedAccountName() throws Exception {
		expenseUpdate.setExpenseId("test");
		expenseUpdate.setLinkedAccount(new LinkedAccount("null", null));
		updateExpensesRequestModel = new UpdateExpensesRequestModel(Collections.singletonList(expenseUpdate));
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseUpdates[0].linkedAccount.name is missing."));

	}

	@Test
	void testUpdateExpenseNoExpenseUpdates() throws Exception {
		expenseUpdate.setExpenseId("test");
		updateExpensesRequestModel = new UpdateExpensesRequestModel(null);
		mockMvc.perform(put("/expenses/api/v1")
				.content(om.writeValueAsString(updateExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseUpdates is missing."));

	}

	@Test
	void KTestDeleteExpenses() throws Exception {
		deleteExpensesRequestModel = new DeleteExpensesRequestModel(Arrays.asList(
				getExpenseId1(), getExpenseId2(), getExpenseId3()
		));
		mockMvc.perform(put("/expenses/api/v1/delete")
				.content(om.writeValueAsString(deleteExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void testDeleteExpensesNotSuccessful() throws Exception {
		deleteExpensesRequestModel = new DeleteExpensesRequestModel(Collections.singletonList("abc"));
		mockMvc.perform(put("/expenses/api/v1/delete")
				.content(om.writeValueAsString(deleteExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses[0].alterSuccessful").value(false));

	}

	@Test
	void testDeleteExpensesNoExpenseDeletions() throws Exception {
		deleteExpensesRequestModel = new DeleteExpensesRequestModel(null);
		mockMvc.perform(put("/expenses/api/v1/delete")
				.content(om.writeValueAsString(deleteExpensesRequestModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Required param expenseDeletions is missing."));

	}

}
