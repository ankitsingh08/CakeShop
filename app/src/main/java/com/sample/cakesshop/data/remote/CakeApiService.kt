package com.sample.cakesshop.data.remote

import com.sample.cakesshop.data.model.Cake
import retrofit2.http.GET

/**
 * Created by AnkitSingh on 12/26/22.
 */
interface CakeApiService {

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/"
    }

    @GET("t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client")
    suspend fun getCakesList(): List<Cake>

}