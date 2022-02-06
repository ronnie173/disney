package com.example.disneyproject.network

import com.example.disneyproject.models.ComicsModel
import com.example.disneyproject.models.Result
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ComicsApi {
    @GET("v1/public/comics")
    suspend fun getAllComics(
        @Query("startYear") startYear: Int,
        @Query("hasDigitalIssue") hasDigitalIssue: Boolean,
        @Query("limit") limit: Int,
        @Query("ts") ts: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): ComicsModel
}