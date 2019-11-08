package com.example.vitalii.kotlinretrofirxjavalogin.model

import com.google.gson.annotations.SerializedName

class CreatedBy  {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("username")
    var username: String? = null
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("last_name")
    var lastName: String? = null
    @SerializedName("email")
    var email: String? = null
}
