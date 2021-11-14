package com.bookmarket.reporsitory

import com.bookmarket.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository: CrudRepository<PurchaseModel, Int>{

}
