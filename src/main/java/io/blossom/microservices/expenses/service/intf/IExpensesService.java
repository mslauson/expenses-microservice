/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.intf;

import io.blossom.microservices.expenses.domain.model.request.*;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;

public interface IExpensesService {
    AlterExpenseResponseModel saveExpense(AddExpenseRequestModel requestModel);

    ExpenseListResponseModel batchSaveExpense(ExpensesBatchRequestModel requestModel);

    ExpenseListResponseModel queryExpenses(ExpenseQueryRequestModel requestModel);

    ExpenseListResponseModel updateExpenses(UpdateExpensesRequestModel requestModel);

    ExpenseListResponseModel deleteExpenses(DeleteExpensesRequestModel requestModel);
}
