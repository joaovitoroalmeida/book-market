package com.bookmarket.reporsitory

import com.bookmarket.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int>{

    fun findByNameContaining(name: String): List<CustomerModel>
}