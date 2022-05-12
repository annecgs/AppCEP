package com.example.appcep.model

import retrofit2.http.GET

interface ApiService {

    @GET("json/")
    suspend fun getCep(): Cep

}