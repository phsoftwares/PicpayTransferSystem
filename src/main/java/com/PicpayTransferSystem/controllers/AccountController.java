package com.PicpayTransferSystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PicpayTransferSystem.interfaces.IAccountService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long personId) {
        if (personId == null || personId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        var balance = accountService.getBalanceByPersonId(personId);
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }
}
