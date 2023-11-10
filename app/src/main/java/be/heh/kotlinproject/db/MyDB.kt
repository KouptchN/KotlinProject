package be.heh.kotlinproject.db

import androidx.room.*
import be.heh.kotlinproject.db.UserRecord
@Database(entities = [(UserRecord::class)], version = 1)
abstract class MyDB : RoomDatabase()
{
    abstract fun userDao(): UserDao
}