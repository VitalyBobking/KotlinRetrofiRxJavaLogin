package com.example.vitalii.kotlinretrofirxjavalogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitalii.kotlinretrofirxjavalogin.model.PostReview
import com.example.vitalii.kotlinretrofirxjavalogin.model.Review
import com.example.vitalii.kotlinretrofirxjavalogin.repo.ReviewRepository

class ReviewViewModel : ViewModel() {
    private var reviewRepository: ReviewRepository? = null
    private var reviewMutableLiveData: MutableLiveData<List<Review>>? = null
    private var postReviewMutableLiveData: MutableLiveData<PostReview>? = null

    fun loadReviewProduct(idProduct: Int): MutableLiveData<List<Review>> {
        if (reviewMutableLiveData == null) {
            reviewMutableLiveData = MutableLiveData()
        }
        getRepository()
        return reviewRepository!!.getReviewData(idProduct)
    }

     fun postReviewProduct(
        token: String,
        id: Int,
        rate: Int,
        review: String
    ): MutableLiveData<PostReview> {
        if (postReviewMutableLiveData == null) {
            postReviewMutableLiveData = MutableLiveData()
        }
        getRepository()
        return reviewRepository!!.postReviewsProduct(token, id, rate, review)
    }

    private fun getRepository() {
        if (reviewRepository == null)
            reviewRepository = ReviewRepository.instance
    }

    override fun onCleared() {
        reviewRepository!!.getDisposable()
        super.onCleared()
    }
}
