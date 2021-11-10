package com.bookmarket.controller

import com.bookmarket.controller.request.BookRequest
import com.bookmarket.extension.toBookModel
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    val bookService : BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody bookRequest: BookRequest) {
        val customer = customerService.getCustomerById(bookRequest.customerId)
        bookService.createBook(bookRequest.toBookModel(customer))
    }
}