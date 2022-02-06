package com.example.disneyproject.ui.home

import com.example.disneyproject.BuildConfig
import com.example.disneyproject.network.ComicsApi
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher.PRIVATE_KEY
import javax.inject.Inject

/**
 * Home repository
 *
 * @property api
 * @constructor Create empty Home repository
 */
class HomeRepository @Inject constructor(private val api: ComicsApi) {

    /**
     * Get all comics
     * Suspend function to make api call
     */
    suspend fun getAllComics() = api.getAllComics(
        2021,
        true,
        10,
        Date().time,
        BuildConfig.API_KEY,
        md5Hash("${Date().time}${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}")
    )

    /**
     * Md5hash
     *
     * @param str
     * @return
     */
    private fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}