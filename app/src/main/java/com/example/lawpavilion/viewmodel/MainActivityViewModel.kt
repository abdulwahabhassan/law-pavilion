package com.example.lawpavilion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.domain.model.Judgement
import com.example.lawpavilion.domain.usecase.RetrieveAllFoldersUseCase
import com.example.lawpavilion.domain.usecase.RetrieveFolderUseCase
import com.example.lawpavilion.domain.usecase.SaveFolderUseCase
import com.example.lawpavilion.ui.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val retrieveAllFoldersUseCase: RetrieveAllFoldersUseCase,
    private val retrieveFolderUseCase: RetrieveFolderUseCase,
    private val saveFolderUseCase: SaveFolderUseCase
) : ViewModel() {

    init {
        loadLatestJudgementsFolders()
    }

    class SearchData(
        val folders: List<Folder> = emptyList(),
        val judgement: Judgement? = folders.getOrNull(0)?.judgement
    )

    private val _searchData: MutableStateFlow<SearchData> = MutableStateFlow(SearchData())
    val searchData: StateFlow<SearchData> = _searchData

    private val _folders: MutableStateFlow<List<Folder>> = MutableStateFlow(emptyList())
    val folders: StateFlow<List<Folder>> = _folders

    private val _judgement: MutableStateFlow<Judgement> = MutableStateFlow(Judgement())
    val judgement: StateFlow<Judgement> = _judgement

    fun loadLatestJudgementsFolders() {
            viewModelScope.launch {
            when (val result = retrieveAllFoldersUseCase.invoke()) {
                is Result.Success -> {
                    _folders.value = result.data
                }
                is Result.Error -> {
                    _folders.value = emptyList()
                }
            }
            }
    }

    fun loadJudgement(folderCode: String) {
            viewModelScope.launch {
                when (val result = retrieveFolderUseCase.invoke(folderCode)) {
                    is Result.Success -> {
                        _judgement.value = result.data.judgement
                    }
                    is Result.Error -> {
                        _judgement.value = Judgement()
                    }
                }
            }
    }

    fun searchLatestJudgements(keyWord: String) {
        //Todo
    }

}