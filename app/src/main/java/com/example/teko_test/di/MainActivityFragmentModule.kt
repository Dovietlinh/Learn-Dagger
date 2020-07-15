package com.example.teko_test.di

import com.example.teko_test.ui.details.DetailsFragment
import com.example.teko_test.ui.home.HomeFragment
import com.example.teko_test.ui.info.ComparingFragment
import com.example.teko_test.ui.info.DescriptionFragment
import com.example.teko_test.ui.info.SpecificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeDescriptionFragment(): DescriptionFragment

    @ContributesAndroidInjector
    abstract fun contributeComparingFragment(): ComparingFragment

    @ContributesAndroidInjector
    abstract fun contributeSpecificationsFragment(): SpecificationsFragment

}