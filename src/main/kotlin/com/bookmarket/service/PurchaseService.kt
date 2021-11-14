package com.bookmarket.service

import com.bookmarket.events.PurchaseEvent
import com.bookmarket.model.PurchaseModel
import com.bookmarket.reporsitory.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    val purchaseRepository: PurchaseRepository,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchase: PurchaseModel) {
        purchaseRepository.save(purchase)
    }
}
