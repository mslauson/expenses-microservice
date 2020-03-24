/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.impl;

import io.blossom.microservices.expenses.dao.IExpensesRepository;
import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import io.blossom.microservices.expenses.domain.model.Expense;
import io.blossom.microservices.expenses.domain.model.ExpenseUpdate;
import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpenseQueryRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpensesBatchRequestModel;
import io.blossom.microservices.expenses.domain.model.request.UpdateExpensesRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;
import io.blossom.microservices.expenses.exception.ExpenseNotFoundException;
import io.blossom.microservices.expenses.service.intf.IExpensesService;
import io.blossom.microservices.expenses.util.ExpensesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpensesServiceImpl implements IExpensesService {
    private final ExpensesMapper expensesMapper;
    private final IExpensesRepository expensesRepository;

    @Override
    public AlterExpenseResponseModel saveExpense(AddExpenseRequestModel requestModel) {
        log.info("inside ExpensesServiceImpl.saveExpense");
        long expenseSaveTime = System.currentTimeMillis();
        ExpensesEntity expensesEntity = expensesRepository.save(expensesMapper.requestToEntity(requestModel));
        log.info("Expense save time -> {}ms", System.currentTimeMillis() - expenseSaveTime);
        return new AlterExpenseResponseModel(expensesMapper.entityToResponse(expensesEntity));
    }

    @Override
    public ExpenseListResponseModel batchSaveExpense(ExpensesBatchRequestModel requestModel) {
        log.info("inside ExpensesServiceImpl.batchSaveExpense; {}expenses", requestModel.getExpenses().size());
        long expenseBatchSaveTime = System.currentTimeMillis();
        List<ExpensesEntity> expensesEntities = expensesRepository.saveAll(expensesMapper.requestToEntity(requestModel));
        log.info("batchSaveExpense time -> {}ms", System.currentTimeMillis() - expenseBatchSaveTime);
        return new ExpenseListResponseModel(expensesMapper.entityToResponse(expensesEntities));
    }

    @Override
    public ExpenseListResponseModel queryExpenses(ExpenseQueryRequestModel requestModel) {
        log.info("inside ExpensesServiceImpl.saveExpense");
        long expenseQueryTime = System.currentTimeMillis();
        List<ExpensesEntity> expensesEntities = expensesRepository.findAll(requestModel.getUsername(),
                requestModel.getMonths(),
                requestModel.getName());
        log.info("queryExpenses time -> {}ms", System.currentTimeMillis() - expenseQueryTime);
        if (expensesEntities.isEmpty())
            throw new ExpenseNotFoundException("No expenses found with the given search criteria");
        return new ExpenseListResponseModel(expensesMapper.entityToResponse(expensesEntities));
    }

    @Override
    public ExpenseListResponseModel updateExpenses(UpdateExpensesRequestModel requestModel) {
        List<Expense> expenses = new ArrayList<>();
        requestModel.getExpenseUpdates().forEach(expenseUpdate -> {
            expenses.add(processUpdates(expenseUpdate));
        });
        return new ExpenseListResponseModel(expenses);
    }

    private Expense processUpdates(ExpenseUpdate expenseUpdate) {
        Expense expense;
        Optional<ExpensesEntity> entityOp = expensesRepository.findById(expenseUpdate.getExpenseId());
        if (entityOp.isEmpty()) {
            expense = new Expense(expenseUpdate.getExpenseId(), false);
        } else {
            ExpensesEntity entity = entityOp.get();
            entity.setLinkedAccount(expenseUpdate.getLinkedAccount());
            entity.setLinkedTransactions(expenseUpdate.getLinkedTransactions());
            entity.setLastUpdated(LocalDateTime.now());
            expense = expensesMapper.entityToResponse(entity);
            expense.setAlterSuccessful(true);
        }
        return expense;
    }
}
