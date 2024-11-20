package com.PicpayTransferSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PicpayTransferSystem.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    
    @Query(nativeQuery = true, value = "SELECT a.id, a.input_value, a.output_value, a.initial_balance, a.creation_datetime, a.id_person FROM accounts a WHERE a.id_person = :personId")
    AccountEntity findByPersonId(@Param("personId") Long personId);
}