package com.sample.cakesshop.view.mapper

import com.sample.cakesshop.domain.model.CakeDomainModel
import com.sample.cakesshop.view.model.CakePresentationModel

/**
 * Created by AnkitSingh on 12/26/22.
 */

fun CakeDomainModel.toPresentation(): CakePresentationModel {
    return CakePresentationModel(
        desc = desc,
        image = image,
        title = title
    )
}

fun List<CakeDomainModel>.toItemPresentationList(): List<CakePresentationModel> {
    return this.map {
        it.toPresentation()
    }.distinctBy { it.title }.sortedBy { it.title }
}
