package com.example.vitalii.kotlinretrofirxjavalogin.repo

import androidx.lifecycle.MutableLiveData
import com.example.vitalii.kotlinretrofirxjavalogin.model.Product
import com.example.vitalii.kotlinretrofirxjavalogin.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductRepository {
    private val disposable = CompositeDisposable()
    private val productMutableLiveData = MutableLiveData<List<Product>>()

    fun getProducts(): MutableLiveData<List<Product>> {
        val productObservable = NetworkService.instance.jsonApi().getProducts()
        disposable.add(productObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { products -> productMutableLiveData.value = products}
        )
        return productMutableLiveData
    }
    fun getDisposable() {
        disposable.dispose()
    }
    companion object {
        private var INSTANCE: ProductRepository? = null

        val instance: ProductRepository
            get() {
                if (INSTANCE == null) {
                    INSTANCE = ProductRepository()
                }
                return INSTANCE as ProductRepository
            }
    }
}

