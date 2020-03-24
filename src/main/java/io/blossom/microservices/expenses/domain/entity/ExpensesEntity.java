/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.domain.entity;

import io.blossom.microservices.expenses.domain.model.LinkedAccount;
import io.blossom.microservices.expenses.domain.model.LinkedTransactions;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document("expenses")
public class ExpensesEntity {

    @Id
    private String id;

    private String username;

    private String name;

    private Double amount;

    private LocalDate month;

    private Boolean reoccurring;

    private LinkedAccount linkedAccount;

    private List<LinkedTransactions> linkedTransactions;

    private String notes;

    private LocalDateTime lastUpdated;

    private boolean flaggedForDeletion;

    private LocalDateTime deletionTimeStamp;
}
