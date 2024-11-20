package com.PicpayTransferSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.PicpayTransferSystem.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}