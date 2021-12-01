package com.bookmarket.controller

import com.bookmarket.controller.request.CustomerRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.controller.response.CustomerResponse
import com.bookmarket.controller.response.PurchaseResponse
import com.bookmarket.extension.toCustomerModel
import com.bookmarket.extension.toResponse
import com.bookmarket.security.UserCanOnlyAccessThenOwnResource
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import com.bookmarket.service.PurchaseService
import org.springframework.data.domain.Page
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize

@RestController
@RequestMapping("customers")
class CustomerController(
    private val customerService: CustomerService,
    private val bookService: BookService,
    private val purchaseService: PurchaseService
    ){

    companion object {
        const val CONDITION_AUTHORIZE = "hasRole('ROLE_ADMIN') || #id == authentication.principal.id" //Example const code
    }

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody @Valid customer: CustomerRequest) {
        customerService.createCustomer(customer.toCustomerModel())
    }

    @GetMapping("{id}")
    @UserCanOnlyAccessThenOwnResource
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.getCustomerById(id).toResponse()
    }

    @GetMapping("{id}/sold_books")
    @PreAuthorize(CONDITION_AUTHORIZE)
    fun findSoldBooks(@PathVariable id: Int, @PageableDefault(page = 0, size = 5) pageable: Pageable): Page<BookResponse> {
        return bookService.findSoldBooksByCustomerId(id, pageable).map { it.toResponse() }
    }

    @GetMapping("{id}/purchased_books")
    @PreAuthorize(CONDITION_AUTHORIZE)
    fun findPurchasedBooks(@PathVariable id: Int, @PageableDefault(page = 0, size = 5) pageable: Pageable): Page<PurchaseResponse> {
        return purchaseService.findPurchasedBooksByCustomerId(id, pageable).map { it.toResponse() }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(CONDITION_AUTHORIZE)
    fun updateCustomer(@PathVariable id: Int, @RequestBody @Valid customer: CustomerRequest){
        val customerSaved = customerService.getCustomerById(id)
        customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCanOnlyAccessThenOwnResource
    fun deleteCustomer(@PathVariable id: Int){
        customerService.deleteCustomer(id)
    }
}