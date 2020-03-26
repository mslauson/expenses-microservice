/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkedAccount {
    @NotBlank
    private String accountId;

    @NotBlank
    private String name;
}
