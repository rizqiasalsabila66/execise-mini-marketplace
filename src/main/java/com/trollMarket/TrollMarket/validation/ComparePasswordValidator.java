package com.trollMarket.TrollMarket.validation;

import com.trollMarket.TrollMarket.utility.MapperHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ComparePasswordValidator implements ConstraintValidator<ComparePassword, Object> {

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext constraintValidatorContext) {
        var password = MapperHelper.getStringField(dto, "password");
        var passwordConfirmation = new BeanWrapperImpl(dto).getPropertyValue("passwordConfirmation").toString();
        return password.equals(passwordConfirmation);

    }
}
