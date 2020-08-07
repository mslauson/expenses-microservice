/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.controller;

import io.blossom.microservices.expenses.domain.model.request.*;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.domain.model.response.ExpenseListResponseModel;
import io.blossom.microservices.expenses.service.intf.IExpensesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/expenses/api/v1")
@RestController
public class ExpensesController {

    private final IExpensesService expensesService;

    @PostMapping
    public AlterExpenseResponseModel addExpensesV1(@Valid @RequestBody AddExpenseRequestModel requestModel) {
        log.info("addExpensesV1 request[{}]", requestModel.toString());
        return expensesService.saveExpense(requestModel);
    }

    @PostMapping("/batch")
    public ExpenseListResponseModel batchAddExpensesV1(@Valid @RequestBody ExpensesBatchRequestModel requestModel) {
        log.info("batchAddExpensesV1 request[{}]", requestModel.toString());
        return expensesService.batchSaveExpense(requestModel);
    }

    @PutMapping
    public ExpenseListResponseModel updateExpensesV1(@Valid @RequestBody UpdateExpensesRequestModel requestModel) {
        log.info("updateExpensesV1 request[{}]", requestModel.toString());
        return expensesService.updateExpenses(requestModel);
    }

    @PutMapping("/delete")
    public ExpenseListResponseModel deleteExpensesV1(@Valid @RequestBody DeleteExpensesRequestModel requestModel) {
        log.info("deleteExpensesV1 request[{}]", requestModel.toString());
        return expensesService.deleteExpenses(requestModel);
    }

    @GetMapping("/{phone}")
    public ExpenseListResponseModel getExpenseV1(@Valid ExpenseQueryRequestModel requestModel) {
        log.info("getExpensesV1 request[{}]", requestModel.toString());
        return expensesService.queryExpenses(requestModel);
    }

}
