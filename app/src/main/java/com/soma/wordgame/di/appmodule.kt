package com.soma.wordgame.di

import com.soma.wordgame.Repository
import com.soma.wordgame.presentation.all_levels.AllLevelViewModel
import com.soma.wordgame.presentation.home_screen.HomeViewModel
import com.soma.wordgame.presentation.level.LevelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appmodule = module {
    single<Repository> {
        Repository(get())
    }
    viewModel<HomeViewModel>{
        HomeViewModel(get())
    }

    viewModel<LevelViewModel>{
        LevelViewModel(get())
    }

    viewModel<AllLevelViewModel>{
        AllLevelViewModel(get())
    }

}