package com.PicpayTransferSystem.interfaces;

import java.math.BigDecimal;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.entities.AccountEntity;
import com.PicpayTransferSystem.entities.PersonEntity;

public interface IAccountService {
    void createNewAccount(PersonEntity personEntity, BigDecimal initialBalance);
    BigDecimal getBalanceByPersonId(Long personId);
    AccountEntity getAccountByPersonId(Long personId);
    boolean executeTransaction(TransactionInputDTO transactionInputDTO);
}