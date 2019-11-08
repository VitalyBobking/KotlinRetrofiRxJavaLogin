package com.example.vitalii.kotlinretrofirxjavalogin.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vitalii.kotlinretrofirxjavalogin.model.PostReview
import com.example.vitalii.kotlinretrofirxjavalogin.model.Review
import com.example.vitalii.kotlinretrofirxjavalogin.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReviewRepository {
    private val disposable = CompositeDisposable()
    private val reviewMutableLiveData = MutableLiveData<List<Review>>()
    private val postReviewMutableLiveData = MutableLiveData<PostReview>()
    fun getReviewData(idProduct: Int): MutableLiveData<List<Review>> {
        val observable = NetworkService.instance.getReviewProducts().getReview(idProduct)
        disposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ reviews -> reviewMutableLiveData.setValue(reviews) },
                    { error -> Log.e("error", error.message.toString()) })
        )
        return reviewMutableLiveData
    }

    fun postReviewsProduct(
        token: String,
        idProduct: Int,
        rate: Int,
        review: String
    ): MutableLiveData<PostReview> {
        val observable = NetworkService.instance.sendReviewProduct().postReview(
            "Token $token", idProduct, rate, review
        )
        disposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { reviews -> postReviewMutableLiveData.setValue(reviews)},
                    { error -> Log.e("error", error.message.toString())})
        )

        return postReviewMutableLiveData
    }

    fun getDisposable() {
        disposable.dispose()
    }

    companion object {
        private var INSTANCE: ReviewRepository? = null
        val instance: ReviewRepository
            get() {
                if (INSTANCE == null) {
                    INSTANCE = ReviewRepository()
                }
                return INSTANCE as ReviewRepository
            }
    }
}
