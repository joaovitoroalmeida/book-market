package com.bookmarket.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [AvailableBookValidator::class])
annotation class AvailableBooks (
    val message: String = "Livro informado não está disponível para compra",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)