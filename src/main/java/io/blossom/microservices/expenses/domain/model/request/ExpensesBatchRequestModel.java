/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model.request;

import io.blossom.microservices.expenses.domain.model.Expense;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesBatchRequestModel {
    @Valid
    private List<Expense> expenses;
}
