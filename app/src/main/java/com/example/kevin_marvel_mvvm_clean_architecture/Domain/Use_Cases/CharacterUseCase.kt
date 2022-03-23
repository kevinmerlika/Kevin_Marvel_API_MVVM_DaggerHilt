package com.example.kevin_marvel_mvvm_clean_architecture.Domain.Use_Cases

import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Models.Characters
import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Repository.MarvelRepository
import com.example.kevin_marvel_mvvm_clean_architecture.Util.Response
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val repository: MarvelRepository
){
    operator fun invoke(offset: Int) : kotlinx.coroutines.flow.Flow<Response<List<Characters>>> = flow{
            try {
                    emit(Response.Loading<List<Characters>>())
                    val list = repository.getAllCharacters(offset = offset).data.results.map {
                        it.toCharacter()
                    }
                emit(Response.Success<List<Characters>>(list))
            }
            catch(e:HttpException){
                emit(Response.Error<List<Characters>>(e.printStackTrace().toString()))

            }

            catch (e: IOException){
                emit(Response.Error<List<Characters>>(e.printStackTrace().toString()))
            }

    }
    }