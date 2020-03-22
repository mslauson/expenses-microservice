/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.intf;

import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossombudgeting.util.budgetcommonutil.model.GenericSuccessResponseModel;

public interface IExpensesService {
    GenericSuccessResponseModel saveExpense(AddExpenseRequestModel addExpenseRequestModel);
}
