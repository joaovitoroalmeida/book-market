package com.bookmarket.controller.response

import com.bookmarket.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
    val id: Int? = null,
    val name: String,
    val price: BigDecimal,
    val customer: CustomerResponse? = null,
    val status: BookStatus? = null
)
