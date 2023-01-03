package com.sample.cakesshop.data.repository

import com.sample.cakesshop.data.mapper.toCakeDomainList
import com.sample.cakesshop.data.remote.CakeApiService
import com.sample.cakesshop.domain.model.ApiResponse
import com.sample.cakesshop.domain.model.CakeDomainModel
import com.sample.cakesshop.domain.repository.CakeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/26/22.
 */
class CakeRepositoryImpl @Inject constructor(private val cakeApiService: CakeApiService) :
    CakeRepository {

    override fun getCakesList(): Flow<ApiResponse<List<CakeDomainModel>>> {
        return flow {
            try {
                val response = cakeApiService.getCakesList()
                emit(ApiResponse.Success(response.toCakeDomainList()))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }
}