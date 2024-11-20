package com.PicpayTransferSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PicpayTransferSystem.entities.AccountEntity;
import com.PicpayTransferSystem.entities.PersonEntity;
import com.PicpayTransferSystem.interfaces.IAccountService;
import com.PicpayTransferSystem.repositories.AccountRepository;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createNewAccount(PersonEntity personEntity, double initialBalance) {
        var accountEntity = new AccountEntity();
        accountEntity.setOutputValue(0.0);
        accountEntity.setInputValue(0.0);
        accountEntity.setInitialBalance(initialBalance);
        accountEntity.setPerson(personEntity);

        accountRepository.save(accountEntity);
    }

    
}