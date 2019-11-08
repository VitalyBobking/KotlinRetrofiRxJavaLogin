package com.example.vitalii.kotlinretrofirxjavalogin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import kotlinx.coroutines.CoroutineScope

@Database(entities = [LoginUser::class], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_database"
                )
                    .addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        /*private class UserDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.loginDao())
                        Log.e("populateDatabase", "populateDatabase")
                    }
                }
            }
        }

        suspend fun populateDatabase(loginDao: LoginDao) {
            //loginDao.deleteAll()
            val user = LoginUser()
            loginDao.insert(user)
        }*/

    }
}