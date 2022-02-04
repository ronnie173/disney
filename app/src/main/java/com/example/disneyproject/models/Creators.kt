package com.example.disneyproject.models

import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("available")
    val available: String,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemX>,
    @SerializedName("returned")
    val returned: String
)