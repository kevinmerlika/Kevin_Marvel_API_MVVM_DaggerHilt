package com.example.kevin_marvel_mvvm_clean_architecture.Ui.LIstCharacters

import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Models.Characters

data class MarvelListResponse(
    val isLoading : Boolean = false,
    val characterList: List<Characters> = emptyList(),
    val error: String = ""
)