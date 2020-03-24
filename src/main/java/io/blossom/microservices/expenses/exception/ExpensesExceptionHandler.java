/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.exception;

import io.blossombudgeting.util.budgetcommonutil.exception.GenericExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpensesExceptionHandler extends GenericExceptionHandler {

}
