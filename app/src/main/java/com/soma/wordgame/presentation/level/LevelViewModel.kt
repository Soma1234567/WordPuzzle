package com.soma.wordgame.presentation.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.wordgame.Repository
import kotlinx.coroutines.launch

class LevelViewModel(
    private val repository: Repository
):ViewModel() {

    fun savelevel(level:Int){
        viewModelScope.launch {
            repository.saveLevel(level)
        }
    }
}