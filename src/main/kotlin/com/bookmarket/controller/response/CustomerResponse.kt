package com.bookmarket.controller.response

import com.bookmarket.enums.CustomerStatus

data class CustomerResponse(
    var id: Int?,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
