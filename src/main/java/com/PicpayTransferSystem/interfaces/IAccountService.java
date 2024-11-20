package com.PicpayTransferSystem.interfaces;

import java.math.BigDecimal;

import com.PicpayTransferSystem.entities.PersonEntity;

public interface IAccountService {
    void createNewAccount(PersonEntity personEntity, BigDecimal initialBalance);

}