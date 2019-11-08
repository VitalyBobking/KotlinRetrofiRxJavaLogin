package com.example.vitalii.kotlinretrofirxjavalogin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser

@Dao
interface LoginDao {
    @Query("SELECT * FROM user")
    fun getUserData(): LiveData<List<LoginUser>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(loginUser: LoginUser)

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}