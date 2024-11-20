package com.PicpayTransferSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.PicpayTransferSystem.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}