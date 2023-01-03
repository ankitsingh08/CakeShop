package com.sample.cakesshop.data.model

import com.sample.cakesshop.domain.model.CakeDomainModel

data class Cake(
    val desc: String,
    val image: String,
    val title: String
)

fun Cake.toDomainModel(): CakeDomainModel {
    return CakeDomainModel(
        desc = desc,
        image = image,
        title = title
    )
}