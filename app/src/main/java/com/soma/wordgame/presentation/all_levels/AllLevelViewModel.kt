package com.soma.wordgame.presentation.all_levels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.wordgame.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AllLevelViewModel(
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