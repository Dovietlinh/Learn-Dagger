package com.example.teko_test.data.mapper

import com.example.teko_test.data.model.request.AttributeGroups
import com.example.teko_test.data.model.request.Image
import com.example.teko_test.data.model.request.Price
import com.example.teko_test.data.model.request.Product
import com.example.teko_test.data.model.request.ProductDetails
import com.example.teko_test.data.model.request.ProductDetailsRequest
import com.example.teko_test.data.model.request.SeoInfo
import com.example.teko_test.data.model.response.Owner
import com.example.teko_test.data.model.response.ProductAttribute
import com.example.teko_test.data.model.response.ProductEntity

object ProductMapper {
    fun productEntityMapToProduct(productEntity: ProductEntity): Product {
        val price: Price = Price(productEntity.supplierSalePrice, productEntity.sellPrice)
        val images: MutableList<Image> = mutableListOf()
        val splitImage: List<String> = productEntity.image.trim().split("\\s".toRegex())
        if (splitImage.isNotEmpty()) {
            splitImage.forEach {
                images.add(Image(it))
            }
        }
        return Product(productEntity.sku, productEntity.name, images, price)
    }

    fun productMapToProductEntity(product: Product): ProductEntity {
        var image = ""
        product.images.forEach {
            image += it.ulr + " "
        }
        return ProductEntity(
            product.sku,
            product.name,
            image,
            product.price.supplierSalePrice,
            product.price.sellPrice
        )
    }

    fun productMapToOwner(product: Product): Owner {
        var image = ""
        product.images.forEach {
            image += it.ulr + " "
        }
        return Owner(
            product.sku,
            product.name,
            image,
            product.price.supplierSalePrice,
            product.price.sellPrice
        )
    }

    fun productRequestToOwner(p: ProductDetailsRequest): Owner {
        return Owner(
            p.result.product.sku,
            p.result.product.name,
            p.result.product.images.toString(),
            p.result.product.price.supplierSalePrice,
            p.result.product.price.sellPrice,
            p.result.product.seoInfo.description
        )
    }

    fun productRequestToAttribute(p: ProductDetailsRequest): List<ProductAttribute>{
        val attributes: MutableList<ProductAttribute> = mutableListOf()
        p.result.product.attributeGroups.forEach {
            attributes.add(
                ProductAttribute(
                    productDetailsEntityID = p.result.product.sku,
                    name = it.name,
                    value = it.value
                )
            )
        }
        return attributes
    }

    fun productDetailsEntityToProductRequest(owner: Owner, productAttribute: List<ProductAttribute>): ProductDetails {
        val splitImage = owner.images.trim().split("\\s".toRegex())
        val images: MutableList<Image> = mutableListOf()
        if (splitImage.isNotEmpty()) {
            splitImage.forEach {
                images.add(Image(it))
            }
        }
        val attributes: MutableList<AttributeGroups> = mutableListOf()
        productAttribute.forEach {
            attributes.add(AttributeGroups(it.name, it.value))
        }
        return ProductDetails(
            owner.sku, owner.name, images, Price(owner.supplierSalePrice, owner.sellPrice),
            SeoInfo(owner.description), attributes
        )
    }
}