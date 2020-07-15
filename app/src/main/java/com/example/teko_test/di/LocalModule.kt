package com.example.teko_test.di

import android.app.Application
import androidx.room.Room
import com.example.teko_test.common.ProductConst.DB_NAME
import com.example.teko_test.data.model.response.ProductDao
import com.example.teko_test.data.model.response.ProductRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {
    @Provides
    @Singleton
    fun movieDatabase(application: Application): ProductRoomDatabase {
        val tempInstance: ProductRoomDatabase? = null
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            return Room.databaseBuilder(
                application,
                ProductRoomDatabase::class.java,
                DB_NAME
            ).allowMainThreadQueries().build()
        }
    }

    @Provides
    @Singleton
    fun binLocalDao(productRoomDatabase: ProductRoomDatabase): ProductDao {
        return productRoomDatabase.productDao()
    }
}