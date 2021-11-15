package com.bookmarket.controller.response

import java.math.BigDecimal

class PurchaseResponse(
    var books: MutableList<BookResponse>,
    var price: BigDecimal,
)