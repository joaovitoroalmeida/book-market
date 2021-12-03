package com.bookmarket.controller

import com.bookmarket.controller.request.BookRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.controller.response.PageResponse
import com.bookmarket.extension.toBookModel
import com.bookmarket.extension.toPageResponse
import com.bookmarket.extension.toResponse
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import javax.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    private val bookService : BookService,
    private val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid bookRequest: BookRequest) {
        val customer = customerService.getCustomerById(bookRequest.customerId)
        bookService.createBook(bookRequest.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> =
        bookService.findAll(pageable).map { it.toResponse() }.toPageResponse()


    @GetMapping("active")
    fun findActivesBooks(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> =
        bookService.findActives(pageable).map { it.toResponse() }.toPageResponse()

    @GetMapping("{id}")
    fun findBookById(@PathVariable id: Int) : BookResponse {
       return bookService.findById(id).toResponse()
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id: Int, @Valid @RequestBody bookRequest: BookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(bookRequest.toBookModel(bookSaved))
    }
}