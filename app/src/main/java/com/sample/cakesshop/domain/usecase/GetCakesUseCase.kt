package com.sample.cakesshop.domain.usecase

import com.sample.cakesshop.di.IoDispatcher
import com.sample.cakesshop.domain.model.ApiResponse
import com.sample.cakesshop.domain.model.CakeDomainModel
import com.sample.cakesshop.domain.repository.CakeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/26/22.
 */
class GetCakesUseCase @Inject constructor(
    private val repository: CakeRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseUseCase<String, List<CakeDomainModel>>(ioDispatcher) {

    override fun execute(vararg parameters: String): Flow<ApiResponse<List<CakeDomainModel>>> {
        return repository.getCakesList()
    }

}