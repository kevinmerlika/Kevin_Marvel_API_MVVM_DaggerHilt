package com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource.Model


import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemX>,
    @SerializedName("returned")
    val returned: Int
)