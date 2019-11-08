package com.example.vitalii.kotlinretrofirxjavalogin.database

import androidx.lifecycle.LiveData
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser

class UserRepository(private val loginDao: LoginDao) {

    val allUsers: LiveData<List<LoginUser>> = loginDao.getUserData()

    suspend fun insert(loginUser: LoginUser) {
        loginDao.insert(loginUser)
    }
    suspend fun delete(){
        loginDao.deleteUser()
    }
}