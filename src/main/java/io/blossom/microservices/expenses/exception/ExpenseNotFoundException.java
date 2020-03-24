/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.exception;

import io.blossombudgeting.util.budgetcommonutil.exception.GenericNotFoundException;

public class ExpenseNotFoundException extends GenericNotFoundException {
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
