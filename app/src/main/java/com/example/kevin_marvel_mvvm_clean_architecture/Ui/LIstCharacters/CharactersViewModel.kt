package com.example.kevin_marvel_mvvm_clean_architecture.Ui.LIstCharacters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Use_Cases.CharacterUseCase
import com.example.kevin_marvel_mvvm_clean_architecture.Util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharacterUseCase
) : ViewModel() {
    private val marvelval = MutableStateFlow(MarvelListResponse())
    var _marvelval : StateFlow<MarvelListResponse> = marvelval

    fun getAllCharData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase(offset = offset).collect{
            when(it){
                is Response.Success ->{
                    marvelval.value = MarvelListResponse(characterList = it.data?: emptyList())
                    Log.d("VIEWMODELLLLL", "${it.data}")
                }
                is Response.Loading ->{
                    marvelval.value = MarvelListResponse(isLoading = true)

                }
                is Response.Error ->{
                    marvelval.value = MarvelListResponse(error = it.message?: "No connection")

                }
            }
        }

    }
}