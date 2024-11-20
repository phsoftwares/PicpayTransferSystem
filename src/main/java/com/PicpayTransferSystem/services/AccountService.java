package com.PicpayTransferSystem.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PicpayTransferSystem.dtos.TransactionInputDTO;
import com.PicpayTransferSystem.entities.AccountEntity;
import com.PicpayTransferSystem.entities.PersonEntity;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.repositories.AccountRepository;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createNewAccount(PersonEntity personEntity, BigDecimal initialBalance) {
        var accountEntity = new AccountEntity(personEntity, initialBalance);
        accountEntity.setPerson(personEntity);

        accountRepository.save(accountEntity);
    }

    @Override
    public BigDecimal getBalanceByPersonId(Long personId) {
        var accountEntity = getAccountByPersonId(personId);
        return accountEntity.getBalance();
    }

    @Override
    public AccountEntity getAccountByPersonId(Long personId) {
        var accountEntity = accountRepository.findByPersonId(personId);
        return accountEntity;
    }

    @Override
    public boolean executeTransaction(TransactionInputDTO transactionInputDTO) {
        var payerEntity = getAccountByPersonId(transactionInputDTO.getIdPayer());
        var payeeEntity = getAccountByPersonId(transactionInputDTO.getIdPayee());

        var payerOutputValue = payerEntity.getOutputValue();
        payerOutputValue = payerOutputValue.add(transactionInputDTO.getValue());
        payerEntity.setOutputValue(payerOutputValue);

        accountRepository.save(payerEntity);

        var payeeInputValue = payeeEntity.getInputValue();
        payeeInputValue = payeeInputValue.add(transactionInputDTO.getValue());
        payeeEntity.setInputValue(payeeInputValue);

        accountRepository.save(payeeEntity);

        return true;
    }
    
}