/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.util;

import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import io.blossom.microservices.expenses.domain.model.Expense;
import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import org.springframework.stereotype.Component;

@Component
public class ExpensesMapper {

    public ExpensesEntity requestToEntity(AddExpenseRequestModel requestModel) {
        return buildExpensesEntity(requestModel);
    }

    private ExpensesEntity buildExpensesEntity(Expense expense) {
        return ExpensesEntity.builder()
                .username(expense.getUsername())
                .name(expense.getName())
                .amount(expense.getAmount())
                .month(expense.getMonth())
                .reoccurring(expense.isReoccurring())
                .notes(expense.getNotes())
                .linkedAccount(expense.getLinkedAccount())
                .linkedTransactions(expense.getLinkedTransactions())
                .lastUpdated(expense.getLastUpdated())
                .build();
    }

    public Expense buildExpense(ExpensesEntity expensesEntity) {
        return Expense.builder()
                .id(expensesEntity.getId())
                .username(expensesEntity.getUsername())
                .name(expensesEntity.getName())
                .amount(expensesEntity.getAmount())
                .month(expensesEntity.getMonth())
                .reoccurring(expensesEntity.isReoccurring())
                .notes(expensesEntity.getNotes())
                .linkedAccount(expensesEntity.getLinkedAccount())
                .linkedTransactions(expensesEntity.getLinkedTransactions())
                .lastUpdated(expensesEntity.getLastUpdated())
                .build();
    }
}
