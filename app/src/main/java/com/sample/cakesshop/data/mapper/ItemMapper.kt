package com.sample.cakesshop.data.mapper

import com.sample.cakesshop.data.model.Cake
import com.sample.cakesshop.data.model.toDomainModel
import com.sample.cakesshop.domain.model.CakeDomainModel

/**
 * Created by AnkitSingh on 12/26/22.
 */
fun List<Cake>.toCakeDomainList(): List<CakeDomainModel> {
    return this.map {
        it.toDomainModel()
    }
}