package com.example.teko_test

import com.example.teko_test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ProductApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
//        DaggerAppComponent.builder()
//            .application(this)
//            .build().inject(this)
//
    }

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        DaggerAppComponent.builder()
//            .localModule(LocalModule(this))
//            .build().inject(this)
//    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}