/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.intf;

import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;

public interface IExpensesService {
    AlterExpenseResponseModel saveExpense(AddExpenseRequestModel addExpenseRequestModel);
}
