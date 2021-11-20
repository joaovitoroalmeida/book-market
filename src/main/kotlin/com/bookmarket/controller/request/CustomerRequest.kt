package com.bookmarket.controller.request

import com.bookmarket.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerRequest (

    @field:NotEmpty(message = "Nome deve ser informado")
    val name: String,
    @EmailAvailable
    @field:Email(message = "Email deve ser válido")
    val email: String,
    val password: String
)