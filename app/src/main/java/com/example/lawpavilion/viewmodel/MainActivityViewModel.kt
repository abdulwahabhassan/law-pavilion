package com.example.lawpavilion.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.domain.usecase.RetrieveAllFoldersUseCase
import com.example.lawpavilion.domain.usecase.RetrieveFolderUseCase
import com.example.lawpavilion.ui.utils.Result
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val retrieveAllFoldersUseCase: RetrieveAllFoldersUseCase,
    private val retrieveFolderUseCase: RetrieveFolderUseCase,
) : ViewModel() {

    init {
        loadLatestJudgementsFolders()
    }

    private val _folders: MutableStateFlow<List<Folder>> = MutableStateFlow(emptyList())
    val folders: StateFlow<List<Folder>> = _folders

    fun loadLatestJudgementsFolders() {
            viewModelScope.launch {
            when (val result = retrieveAllFoldersUseCase.invoke()) {
                is Result.Success -> {
                    _folders.value = result.data
                    Timber.d("success: ${result.data}")
                }
                is Result.Error -> {
                    _folders.value = emptyList()
                    Timber.d("error: ${result.exception}")
                }
            }
            }
    }

}