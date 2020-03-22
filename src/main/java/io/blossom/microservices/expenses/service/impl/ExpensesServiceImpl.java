/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.impl;

import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.service.intf.IExpensesService;
import io.blossombudgeting.util.budgetcommonutil.model.GenericSuccessResponseModel;

public class ExpensesServiceImpl implements IExpensesService {
    @Override
    public GenericSuccessResponseModel saveExpense(AddExpenseRequestModel addExpenseRequestModel) {
        return null;
    }
}
