/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.util;

import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import io.blossom.microservices.expenses.domain.model.Expense;
import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpensesBatchRequestModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpensesMapper {

    public ExpensesEntity requestToEntity(AddExpenseRequestModel requestModel) {
        return buildExpensesEntity(requestModel);
    }

    public List<ExpensesEntity> requestToEntity(ExpensesBatchRequestModel requestModel) {
        List<ExpensesEntity> expensesEntities = new ArrayList<>();
        requestModel.getExpenses().forEach(expense -> expensesEntities.add(buildExpensesEntity(expense)));
        return expensesEntities;
    }

    public Expense entityToResponse(ExpensesEntity expensesEntity) {
        return buildExpense(expensesEntity);
    }

    public List<Expense> entityToResponse(List<ExpensesEntity> expensesEntity) {
        List<Expense> expenses = new ArrayList<>();
        expensesEntity.forEach(entity -> expenses.add(buildExpense(entity)));
        return expenses;
    }

    private ExpensesEntity buildExpensesEntity(Expense expense) {
        return ExpensesEntity.builder()
                .username(expense.getPhone())
                .name(expense.getName())
                .amount(expense.getAmount())
                .month(expense.getMonth())
                .reoccurring(expense.getReoccurring())
                .notes(expense.getNotes())
                .linkedAccount(expense.getLinkedAccount())
                .linkedTransactions(expense.getLinkedTransactions())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

    private Expense buildExpense(ExpensesEntity expensesEntity) {
        return Expense.builder()
                .id(expensesEntity.getId())
                .phone(expensesEntity.getUsername())
                .name(expensesEntity.getName())
                .amount(expensesEntity.getAmount())
                .month(expensesEntity.getMonth())
                .reoccurring(expensesEntity.getReoccurring())
                .notes(expensesEntity.getNotes())
                .linkedAccount(expensesEntity.getLinkedAccount())
                .linkedTransactions(expensesEntity.getLinkedTransactions())
                .lastUpdated(expensesEntity.getLastUpdated())
                .build();
    }
}
