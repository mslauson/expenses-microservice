/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model.request;

import io.blossom.microservices.expenses.domain.model.ExpenseUpdate;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExpensesRequestModel {

    @NotNull
    @Valid
    private List<ExpenseUpdate> expenseUpdates;
}
