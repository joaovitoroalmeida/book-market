package com.bookmarket.validation

import com.bookmarket.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(private var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(email: String?, context: ConstraintValidatorContext?): Boolean {
        if(email.isNullOrBlank())
            return false

       return customerService.emailAvailable(email)
    }
}
