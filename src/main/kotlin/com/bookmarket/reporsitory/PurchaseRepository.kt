package com.bookmarket.reporsitory

import com.bookmarket.model.PurchaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository: CrudRepository<PurchaseModel, Int> {
    fun findByCustomerId(id: Int, pageable: Pageable): Page<PurchaseModel>
}
