package com.bookmarket.extension

import com.bookmarket.controller.request.BookRequest
import com.bookmarket.controller.request.CustomerRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.controller.response.CustomerResponse
import com.bookmarket.controller.response.PurchaseResponse
import com.bookmarket.enums.BookStatus
import com.bookmarket.enums.CustomerStatus
import com.bookmarket.model.BookModel
import com.bookmarket.model.CustomerModel
import com.bookmarket.model.PurchaseModel

fun CustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun CustomerRequest.toCustomerModel(customerSaved: CustomerModel): CustomerModel{
    return CustomerModel(id = customerSaved.id, name = this.name, email = this.email, status = customerSaved.status)
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

fun CustomerModel.toResponse() : CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse() : BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer?.toResponse(),
        status = this.status
    )
}

fun PurchaseModel.toResponse(): PurchaseResponse {
    return PurchaseResponse(
        books = this.books.map { it.toResponse() }.toMutableList(),
        price = this.price
    )
}