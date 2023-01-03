package com.sample.cakesshop.di

import com.sample.cakesshop.data.repository.CakeRepositoryImpl
import com.sample.cakesshop.domain.repository.CakeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by AnkitSingh on 12/26/22.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCakeRepository(cakeRepository: CakeRepositoryImpl): CakeRepository
}