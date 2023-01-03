package com.sample.cakesshop.domain.model

/**
 * Created by AnkitSingh on 12/26/22.
 */
sealed interface ApiResponse<out R> {

    data class Success<out T>(val data: T) : ApiResponse<T>
    data class Error(val exception: Exception) : ApiResponse<Nothing>
}