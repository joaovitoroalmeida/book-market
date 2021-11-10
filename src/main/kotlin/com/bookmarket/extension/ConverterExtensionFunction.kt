package com.bookmarket.extension

import com.bookmarket.controller.request.BookRequest
import com.bookmarket.controller.request.CustomerRequest
import com.bookmarket.enums.BookStatus
import com.bookmarket.model.BookModel
import com.bookmarket.model.CustomerModel

fun CustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email)
}

fun CustomerRequest.toCustomerModel(id: Int): CustomerModel{
    return CustomerModel(id = id, name = this.name, email = this.email)
}

fun BookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name!!,
        price = this.price!!,
        status = BookStatus.ATIVO,
        customer = customer)
}

fun BookRequest.toBookModel(bookModel: BookModel): BookModel {
    return BookModel(
        id = bookModel.id,
        name = this.name?: bookModel.name,
        price = this.price?: bookModel.price,
        status = bookModel.status,
        customer = bookModel.customer)
}