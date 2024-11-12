package com.soma.wordgame.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.wordgame.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
):ViewModel() {

    private var _level = MutableStateFlow(0)
    val level = _level.asStateFlow()
    init {

        viewModelScope.launch {
            _level.value = repository.readLevel().stateIn(viewModelScope).value
        }
    }
}