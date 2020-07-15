package com.example.teko_test.di

import com.example.teko_test.ProductApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [ApiModule::class, LocalModule::class, ViewModelModule::class,
        AppModule::class, AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class, ComposeModule::class,BuilderModule::class]
)
@Singleton
interface AppComponent : AndroidInjector<ProductApplication> {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: ProductApplication): AppComponent
    }
}