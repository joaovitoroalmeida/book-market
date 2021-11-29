package com.bookmarket.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admins")
class AdminController() {

    @GetMapping("reports")
    fun getReport(): String {
        return "This is a Report. Only admins can see it"
    }
}