package be.heh.kotlinproject.db

import androidx.room.*
import be.heh.kotlinproject.db.UserRecord
@Dao
interface UserDao {
    @Query("SELECT * FROM UserTable")
    fun get(): List<UserRecord>
    @Query("SELECT * FROM UserTable WHERE login = :login")
    fun get(login: String): UserRecord
    @Insert
    fun insertUser(vararg listCategories: UserRecord)
    @Update
    fun updatePersonne(task: UserRecord)
    @Delete
    fun deletePersonne(task: UserRecord)
}