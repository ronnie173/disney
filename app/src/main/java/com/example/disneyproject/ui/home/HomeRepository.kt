package com.example.disneyproject.ui.home

import com.example.disneyproject.network.ComicsApi
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ComicsApi) {


    suspend fun getAllComics() = api.getAllComics(1,"48ceddb7a93dab230d537f4536dee0c5","87a169fd664954d31cf83de0c3171ed1")

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}