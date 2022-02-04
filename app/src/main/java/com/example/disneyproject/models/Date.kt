package com.example.disneyproject.models

import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("date")
    val date: String,
    @SerializedName("type")
    val type: String
)