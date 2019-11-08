package com.example.vitalii.kotlinretrofirxjavalogin.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import com.example.vitalii.kotlinretrofirxjavalogin.repo.LoginRepository

class LoginViewModel : ViewModel() {

    private var loginRepository: LoginRepository? = null
    private var userMutableLiveData: MutableLiveData<LoginUser>? = null

    fun loginUser(email: String, password: String): LiveData<LoginUser> {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        getRepository()
        return loginRepository!!.login(email, password)
    }

    private fun getRepository() {
        if (loginRepository == null)
            loginRepository = LoginRepository.instance
    }
    override fun onCleared() {
        super.onCleared()
        loginRepository!!.getDisposable()
    }
}

