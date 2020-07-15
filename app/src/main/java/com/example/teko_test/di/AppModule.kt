package com.example.teko_test.di

import android.app.Application
import android.content.Context
import com.example.teko_test.ProductApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: ProductApplication): Context {
        return application.baseContext
    }

    @Provides
    @Singleton
    fun provideApplication(application: ProductApplication): Application {
        return application
    }
}