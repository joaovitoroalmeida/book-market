package com.bookmarket.controller.response

import com.bookmarket.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerResponse? = null,
    var status: BookStatus? = null
)
