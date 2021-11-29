package com.bookmarket.controller.response

import com.bookmarket.enums.CustomerStatus

data class CustomerResponse(
    val id: Int?,
    val name: String,
    val email: String,
    val status: CustomerStatus
)
