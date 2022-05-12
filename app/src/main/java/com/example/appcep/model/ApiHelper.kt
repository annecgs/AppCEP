package com.example.appcep.model

class ApiHelper(private val apiService: ApiService) {
    suspend fun getCep() = apiService.getCep()
}