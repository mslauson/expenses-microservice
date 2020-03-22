/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.service.impl;

import io.blossom.microservices.expenses.dao.IExpensesRepository;
import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.service.intf.IExpensesService;
import io.blossom.microservices.expenses.util.ExpensesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpensesServiceImpl implements IExpensesService {
    private final ExpensesMapper expensesMapper;
    private final IExpensesRepository expensesRepository;

    @Override
    public AlterExpenseResponseModel saveExpense(AddExpenseRequestModel addExpenseRequestModel) {
        log.info("inside ExpensesServiceImpl.save expense");
        long expenseSaveTime = System.currentTimeMillis();
        ExpensesEntity expensesEntity = expensesRepository.save(expensesMapper.requestToEntity(addExpenseRequestModel));
        log.info("Expense save time -> {}ms", System.currentTimeMillis() - expenseSaveTime);
        return new AlterExpenseResponseModel(expensesMapper.buildExpense(expensesEntity));
    }
}
