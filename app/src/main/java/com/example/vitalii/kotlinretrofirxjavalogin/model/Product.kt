package com.example.vitalii.kotlinretrofirxjavalogin.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.SerializedName

class Product : BaseObservable(){
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("title")
    var title: String? = null
        @Bindable get
        set(title) {
            field = title
            notifyPropertyChanged(BR.title)
        }

    @SerializedName("img")
    var img: String? = null
        @Bindable get
        set(img) {
            field = img
            notifyPropertyChanged(BR.img)
        }

    @SerializedName("text")
    var text: String? = null
        @Bindable get
        set(text) {
            field = text
            notifyPropertyChanged(BR.text)
        }

}
