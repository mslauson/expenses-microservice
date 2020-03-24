/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseUpdate {

    private String expenseId;

    @Valid
    private LinkedAccount linkedAccount;

    @Valid
    private List<LinkedTransactions> linkedTransactions;

    private String notes;
}
