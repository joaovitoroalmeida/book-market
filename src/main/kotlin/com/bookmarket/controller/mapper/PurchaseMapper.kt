package com.bookmarket.controller.mapper

import com.bookmarket.controller.request.PurchaseRequest
import com.bookmarket.model.PurchaseModel
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    val bookService: BookService,
    val customerService: CustomerService
) {
    fun toModel(request: PurchaseRequest): PurchaseModel {
        val customer = customerService.getCustomerById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseModel(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}