package com.example.disneyproject.models

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: String,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("returned")
    val returned: String
)