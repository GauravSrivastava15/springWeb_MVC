package com.learning.springbootWebApplication.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimNoValidator implements ConstraintValidator<PrimeNoValidation, Integer> {

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {

        for(int i = 2; i <= Math.sqrt(integer); i++){
            if(integer % i == 0)
                return false;
        }
        return true;
    }
}
