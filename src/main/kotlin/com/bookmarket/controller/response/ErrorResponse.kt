package com.bookmarket.controller.response

data class ErrorResponse(
    var status: Int,
    var message: String,
    var internalCode: String,
    var erros: List<FieldErrorResponse>?
)

data class FieldErrorResponse(
    var message: String,
    var field: String
)