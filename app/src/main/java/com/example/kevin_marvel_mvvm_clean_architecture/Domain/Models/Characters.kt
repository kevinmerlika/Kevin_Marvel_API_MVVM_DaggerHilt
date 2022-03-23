package com.example.kevin_marvel_mvvm_clean_architecture.Domain.Models

data class Characters(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val thumbnailExt : String,
    val comics: List<String>

)
