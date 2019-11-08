package com.example.vitalii.kotlinretrofirxjavalogin.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.SerializedName

class Review : BaseObservable(){
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("product")

    var product: Int? = null

    @SerializedName("rate")
    var rate: Int? = null
        @Bindable get
        set(rate) {
            field = rate
            notifyPropertyChanged(BR.rate)
        }

    @SerializedName("text")
    var text: String? = null

    @SerializedName("created_by")
    var createdBy: CreatedBy? = null

    @SerializedName("created_at")
    var createdAt: String? = null

}
