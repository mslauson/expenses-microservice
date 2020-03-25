/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model.request;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteExpensesRequestModel {
    @NotNull
    private List<String> expenseDeletions;
}
