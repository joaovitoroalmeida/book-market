package com.bookmarket.model

import com.bookmarket.enums.BookStatus
import com.bookmarket.enums.Errors
import com.bookmarket.exception.BadRequestException
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "book")
data class BookModel(
    @Id
    @GeneratedValue
    val id: Int? = null,

    @Column
    val name: String,

    @Column
    val price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: CustomerModel? = null
) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value){
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO)
                throw BadRequestException(Errors.ML1002.message.format(field), Errors.ML1002.code)

            field = value
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: BookStatus?): this(id, name, price, customer) {
                    this.status = status
                }
}