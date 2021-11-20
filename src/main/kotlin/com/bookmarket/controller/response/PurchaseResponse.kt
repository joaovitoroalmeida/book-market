package com.bookmarket.controller.response

import java.math.BigDecimal

class PurchaseResponse(
    val books: MutableList<BookResponse>,
    val price: BigDecimal,
)