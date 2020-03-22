/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model.response;

import io.blossom.microservices.expenses.domain.model.Expense;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseListResponseModel {
    List<Expense> expenses;
}
