/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model.response;

import io.blossom.microservices.expenses.domain.model.Expense;

public class AlterExpenseResponseModel extends Expense {
    public AlterExpenseResponseModel(Expense expense) {
        super(expense.getId(),
                expense.getPhone(),
                expense.getName(),
                expense.getAmount(),
                expense.getMonth(),
                expense.getReoccurring(),
                expense.getLinkedAccount(),
                expense.getLinkedTransactions(),
                expense.getNotes(),
                expense.getLastUpdated(),
                expense.getAlterSuccessful()
        );
    }
}
