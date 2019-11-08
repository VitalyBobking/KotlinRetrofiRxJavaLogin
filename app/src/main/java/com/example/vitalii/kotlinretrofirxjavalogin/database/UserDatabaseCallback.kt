package com.example.vitalii.kotlinretrofirxjavalogin.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class UserDatabaseCallback(
        private val scope: CoroutineScope,
        private var INSTANCE: UserRoomDatabase? = null
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.loginDao())
                    deleteUser(database.loginDao())
                }
            }
        }

    private suspend fun populateDatabase(loginDao: LoginDao) {
        val user = LoginUser()
        loginDao.insert(user)
    }
    private suspend fun deleteUser(loginDao: LoginDao){
        loginDao.deleteUser()
    }

}