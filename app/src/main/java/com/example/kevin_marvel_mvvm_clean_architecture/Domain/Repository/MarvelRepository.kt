package com.example.kevin_marvel_mvvm_clean_architecture.Domain.Repository

import com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource.Model.CharactersDTO

interface MarvelRepository {
    suspend fun getAllCharacters(offset: Int): CharactersDTO
}