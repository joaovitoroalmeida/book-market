package com.bookmarket.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class BookRequest(

    @field:NotEmpty(message = "Nome deve ser informado")
    val name : String?,
    @field:NotNull(message = "Price deve ser informado")
    val price: BigDecimal?,

    @JsonAlias("customer_id")
    val customerId: Int
)