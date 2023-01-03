package com.sample.cakesshop.domain.repository

import com.sample.cakesshop.domain.model.ApiResponse
import com.sample.cakesshop.domain.model.CakeDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 12/26/22.
 */
interface CakeRepository {

    fun getCakesList(): Flow<ApiResponse<List<CakeDomainModel>>>
}