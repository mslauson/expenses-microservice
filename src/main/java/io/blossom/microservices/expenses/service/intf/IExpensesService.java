/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.intf;

import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpenseQueryRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpensesBatchRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;

public interface IExpensesService {
    AlterExpenseResponseModel saveExpense(AddExpenseRequestModel requestModel);

    ExpenseListResponseModel batchSaveExpense(ExpensesBatchRequestModel requestModel);

    ExpenseListResponseModel queryExpenses(ExpenseQueryRequestModel requestModel);
}
