/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.controller;

import io.blossom.microservices.expenses.domain.model.request.AddExpenseRequestModel;
import io.blossom.microservices.expenses.domain.model.response.AlterExpenseResponseModel;
import io.blossom.microservices.expenses.service.intf.IExpensesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/expenses")
@RestController
public class ExpensesController {

    private final IExpensesService expensesService;

    @PostMapping
    public AlterExpenseResponseModel addExpenseV1(@Valid @RequestBody AddExpenseRequestModel requestModel) {
        log.info("addExpensesV1 request[{}]", requestModel.toString());
        return expensesService.saveExpense(requestModel);
    }

}
