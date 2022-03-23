package com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource

import com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource.Model.CharactersDTO
import com.example.kevin_marvel_mvvm_clean_architecture.Util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey")apikey: String = Constants.API_KEY,
        @Query("ts")ts: String = Constants.timeStamp,
        @Query("hash")hash:String = Constants.hash(),
        @Query("offset")offset: String
    ):CharactersDTO
}