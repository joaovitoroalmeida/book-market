package com.bookmarket.controller.response

data class ErrorResponse(
    val status: Int,
    val message: String,
    val internalCode: String,
    val erros: List<FieldErrorResponse>?
)

data class FieldErrorResponse(
    val message: String,
    val field: String
)