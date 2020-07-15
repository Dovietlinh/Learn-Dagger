package com.example.teko_test.data.model.response

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ProductEntity::class, ProductAttribute::class, Owner::class], version = 1)
abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}