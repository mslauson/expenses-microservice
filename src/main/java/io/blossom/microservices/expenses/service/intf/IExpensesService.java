/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.intf;

import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpensesBatchRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;

public interface IExpensesService {
    AlterExpenseResponseModel saveExpense(AddExpenseRequestModel addExpenseRequestModel);

    ExpenseListResponseModel batchSaveExpense(ExpensesBatchRequestModel requestModel);
}
