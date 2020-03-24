/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.impl;

import io.blossom.microservices.expenses.dao.IExpensesRepository;
import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpenseQueryRequestModel;
import io.blossom.microservices.expenses.domain.model.request.ExpensesBatchRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;
import io.blossom.microservices.expenses.service.intf.IExpensesService;
import io.blossom.microservices.expenses.util.ExpensesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        LocalDate[] months = new LocalDate[requestModel.getMonths().size()];
//        requestModel.getMonths().toArray(months);
        List<ExpensesEntity> expensesEntities = expensesRepository.findAll(requestModel.getUsername(),
                requestModel.getMonths(),
                requestModel.getName());
        log.info("batchSaveExpense time -> {}ms", System.currentTimeMillis() - expenseQueryTime);
        return new ExpenseListResponseModel(expensesMapper.entityToResponse(expensesEntities));
    }
}
