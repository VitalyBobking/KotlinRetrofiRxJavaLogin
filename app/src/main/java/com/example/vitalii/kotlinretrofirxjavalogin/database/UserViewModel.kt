package com.example.vitalii.kotlinretrofirxjavalogin.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:UserRepository
    val allUsers: LiveData<List<LoginUser>>

    init {
        val userDao = UserRoomDatabase.getDatabase(application, viewModelScope).loginDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insert(loginUser: LoginUser) = viewModelScope.launch {
        repository.insert(loginUser)
    }
    fun delete() = viewModelScope.launch { repository.delete() }
}