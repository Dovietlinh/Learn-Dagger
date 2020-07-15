package com.example.teko_test.di

import com.example.teko_test.ui.ViewModelFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComposeModule {
    @ContributesAndroidInjector
    internal abstract fun contributeViewModelFragment(): ViewModelFragment
}