package com.trollMarket.TrollMarket.validation;

import com.trollMarket.TrollMarket.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private AccountService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return  !service.isUsernameExist(value); //dibalik kalau username ada jadinya false, kalo tidak ada true
    }

}
