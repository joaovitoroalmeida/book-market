package com.bookmarket.service

import com.bookmarket.enums.CustomerStatus
import com.bookmarket.enums.Errors
import com.bookmarket.exception.NotFoundException
import com.bookmarket.model.CustomerModel
import com.bookmarket.reporsitory.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val bookService: BookService
    ){

    fun getAll(name: String?): List<CustomerModel> {

        name?.let {
            return customerRepository.findByNameContaining(it)
        }

        return customerRepository.findAll().toList()
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getCustomerById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML1101.message.format(id), Errors.ML1101.code) }
    }

    fun updateCustomer(customer: CustomerModel){
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun deleteCustomer(id: Int){
        val customer = getCustomerById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}