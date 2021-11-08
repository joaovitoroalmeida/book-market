package com.bookmarket.extension

import com.bookmarket.controller.request.CustomerRequest
import com.bookmarket.model.CustomerModel

fun CustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email)
}

fun CustomerRequest.toCustomerModel(id: Int): CustomerModel{
    return CustomerModel(id = id, name = this.name, email = this.email)
}