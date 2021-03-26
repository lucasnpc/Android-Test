package com.example.androidtest.di

import com.example.androidtest.data.LoginRepository
import com.example.androidtest.data.NewsInfoRepository
import com.example.androidtest.data.NewsListRepository
import com.example.androidtest.ui.login.LoginViewModel
import com.example.androidtest.ui.newsInfo.NewsInfoViewModel
import com.example.androidtest.ui.newsList.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory {
        LoginRepository()
    }
    viewModel { LoginViewModel(loginRepository = get()) }
}
val newsListModule = module {
    factory { NewsListRepository() }
    viewModel { NewsListViewModel(newsListRepository = get()) }
}
val newsInfoModule = module {
    factory { NewsInfoRepository() }
    viewModel { NewsInfoViewModel(newsInfoRepository = get()) }
}