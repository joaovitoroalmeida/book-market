package com.bookmarket.controller

import com.bookmarket.controller.request.CustomerRequest
import com.bookmarket.extension.toCustomerModel
import com.bookmarket.model.CustomerModel
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customers")
class CustomerController (val customerService: CustomerService){

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAll(name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: CustomerRequest) {
        customerService.createCustomer(customer.toCustomerModel())
    }

    @GetMapping("{id}")
    fun getCustomer(@PathVariable id: String): CustomerModel {
        return customerService.getCustomer(id)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: String, @RequestBody customer: CustomerRequest){
        customerService.updateCustomer(customer.toCustomerModel(id))
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: String){
        customerService.deleteCustomer(id)
    }
}