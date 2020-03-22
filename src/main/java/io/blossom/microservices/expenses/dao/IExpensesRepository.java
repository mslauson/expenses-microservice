/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.dao;

import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpensesRepository extends MongoRepository<ExpensesEntity, String> {
}
