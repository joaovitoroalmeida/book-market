package com.bookmarket.reporsitory

import com.bookmarket.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository <BookModel, Int>{
}