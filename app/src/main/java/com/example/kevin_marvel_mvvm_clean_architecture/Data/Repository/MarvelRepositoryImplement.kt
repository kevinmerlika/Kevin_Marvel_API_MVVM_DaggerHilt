package com.example.kevin_marvel_mvvm_clean_architecture.Data.Repository

import android.util.Log
import com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource.MarvelApi
import com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource.Model.CharactersDTO
import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImplement @Inject constructor(
    private val api: MarvelApi
):MarvelRepository {
    override suspend fun getAllCharacters(offset: Int): CharactersDTO {
        return api.getAllCharacters(offset = offset.toString())

    }
}