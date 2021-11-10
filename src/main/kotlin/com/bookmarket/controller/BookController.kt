package com.bookmarket.controller

import com.bookmarket.controller.request.BookRequest
import com.bookmarket.extension.toBookModel
import com.bookmarket.model.BookModel
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @GetMapping
    fun findAll(): List<BookModel> =
        bookService.findAll()


    @GetMapping("active")
    fun findActivesBooks(): List<BookModel> =
        bookService.findActives()

    @GetMapping("{id}")
    fun findBookById(@PathVariable id: Int) : BookModel {
       return bookService.findById(id)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id: Int, @RequestBody bookRequest: BookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(bookRequest.toBookModel(bookSaved))
    }
}