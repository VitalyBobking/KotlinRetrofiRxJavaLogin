package com.example.vitalii.kotlinretrofirxjavalogin.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.lang.IllegalArgumentException

@Entity(tableName = "user")
class LoginUser : BaseObservable() {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @SerializedName("username")
    @ColumnInfo(name = "username")
    var username: String? = null
        @Bindable get
        set(username) {
            field = username
            notifyPropertyChanged(BR.username)
        }

    @SerializedName("password")
    @ColumnInfo(name = "password")
    var password: String? = null
    @Bindable get
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }
    @SerializedName("token")
    @ColumnInfo(name = "token")
    var token: String? = null

}