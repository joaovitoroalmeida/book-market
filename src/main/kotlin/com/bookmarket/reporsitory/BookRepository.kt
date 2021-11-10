package com.bookmarket.reporsitory

import com.bookmarket.enums.BookStatus
import com.bookmarket.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository <BookModel, Int>{
    fun findByStatus(status: BookStatus): List<BookModel>
}