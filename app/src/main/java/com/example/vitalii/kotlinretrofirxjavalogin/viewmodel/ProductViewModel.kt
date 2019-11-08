package com.example.vitalii.kotlinretrofirxjavalogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitalii.kotlinretrofirxjavalogin.model.Product
import com.example.vitalii.kotlinretrofirxjavalogin.repo.ProductRepository

class ProductViewModel : ViewModel() {
private var productRepository: ProductRepository? = null
private var productMutableLiveData: MutableLiveData<List<Product>>? = null

    fun loadProducts(): MutableLiveData<List<Product>> {
         if (productMutableLiveData == null) {
         productMutableLiveData = MutableLiveData()
         }
        getRepository()
        return productRepository!!.getProducts()
    }

    private fun getRepository() {
        if (productRepository == null)
            productRepository = ProductRepository.instance
    }
    override fun onCleared() {
        super.onCleared()
        productRepository!!.getDisposable()
    }
}



