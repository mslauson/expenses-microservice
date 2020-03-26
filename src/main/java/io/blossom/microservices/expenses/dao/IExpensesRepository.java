/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.dao;

import io.blossom.microservices.expenses.domain.entity.ExpensesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpensesRepository extends MongoRepository<ExpensesEntity, String> {

    @Query("{'username': ?0, 'flaggedForDeletion' : false, $and : [ { 'month' : { $in : ?1 } }, {'name': {$regex: ?2, $options: 'i'}}] }")
    List<ExpensesEntity> findAll(String username, LocalDate[] months, String name);
}
