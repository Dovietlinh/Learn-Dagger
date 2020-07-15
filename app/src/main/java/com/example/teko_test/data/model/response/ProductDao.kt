package com.example.teko_test.data.model.response

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Observable


@Dao
interface ProductDao {
    @Query("Select * from db_products Where (name LIKE :stringSearch OR sku LIKE :stringSearch)")
    fun getProductByPage(stringSearch: String): Observable<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(productEntity: ProductEntity)

    @Query("Select * from owner where sku LIKE :sku")
    fun getProductDetailsOwner(sku: Int): Observable<Owner>

    @Query("Select * from product_attribute where productDetailsEntityID LIKE :sku")
    fun getAttributeBySku(sku: Int): Observable<List<ProductAttribute>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProductDetailsOwner(owner: Owner)

    @Insert
    fun saveProductAttribute(productAttribute: List<ProductAttribute>)

    @Query("Delete from product_attribute where productDetailsEntityID LIKE :sku")
    fun deleteProductAttribute(sku: Int)
}