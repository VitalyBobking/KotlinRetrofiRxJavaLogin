package com.example.vitalii.kotlinretrofirxjavalogin.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import com.example.vitalii.kotlinretrofirxjavalogin.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginRepository {

    private val disposable = CompositeDisposable()
    private var userMutableLiveData = MutableLiveData<LoginUser>()

    fun login(email: String, password: String): MutableLiveData<LoginUser> {
        val userObservable = NetworkService.instance.login().getToken(email, password)
             disposable.add(
                 userObservable.subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(
                         { user -> userMutableLiveData.setValue(user)},
                         { error(Log.e("error", toString())) })
                 )
        return userMutableLiveData
    }

    companion object {
        private var INSTANCE: LoginRepository? = null
        val instance: LoginRepository
            get() {
                if (INSTANCE == null) {
                    INSTANCE = LoginRepository()
                }
                return INSTANCE as LoginRepository
            }
    }

    fun getDisposable() {
        disposable.dispose()
    }
}
