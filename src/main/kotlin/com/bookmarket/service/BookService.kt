package com.bookmarket.service

import com.bookmarket.enums.BookStatus
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

    fun findAll(): List<BookModel> {
        return bookRepository.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }
}