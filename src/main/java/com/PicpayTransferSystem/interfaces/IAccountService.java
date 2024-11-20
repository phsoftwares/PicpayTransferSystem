package com.PicpayTransferSystem.interfaces;

import com.PicpayTransferSystem.entities.PersonEntity;

public interface IAccountService {
    void createNewAccount(PersonEntity personEntity, double initialBalance);

}