package com.example.vitalii.kotlinretrofirxjavalogin.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostReview : BaseObservable(){
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("product")
    @Expose
    var product: Int? = null
        @Bindable get
        set(product) {
            field = product
            notifyPropertyChanged(BR.product)
        }
    @SerializedName("rate")
    @Expose
    var rate: Int? = null
        @Bindable get
        set(rate) {
            field = rate
            notifyPropertyChanged(BR.rate)
        }
    @SerializedName("text")
    @Expose
    var text: String? = null
        @Bindable get
        set(text) {
            field = text
            notifyPropertyChanged(BR.text)
        }
}
