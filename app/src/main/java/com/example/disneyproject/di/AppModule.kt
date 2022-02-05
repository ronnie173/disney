package com.example.disneyproject.di

import com.example.disneyproject.ui.home.HomeRepository
import com.example.disneyproject.utils.Constants
import com.example.disneyproject.network.ComicsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesComicsRepository(api: ComicsApi) = HomeRepository(api)

    @Singleton
    @Provides
    fun providesComicsApi(): ComicsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicsApi::class.java)
    }


}