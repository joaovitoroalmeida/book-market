package com.bookmarket.service

import com.bookmarket.model.BookModel
import com.bookmarket.reporsitory.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun createBook(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }
}