package com.bookmarket.validation

import com.bookmarket.enums.BookStatus
import com.bookmarket.service.BookService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class AvailableBookValidator(val bookService: BookService): ConstraintValidator<AvailableBooks, Set<Int>> {
    override fun isValid(booksId: Set<Int>?, context: ConstraintValidatorContext?): Boolean {
        if(booksId.isNullOrEmpty())
            return false

        return bookService.findAllByIds(booksId).none { it.status != BookStatus.ATIVO }
    }
}
