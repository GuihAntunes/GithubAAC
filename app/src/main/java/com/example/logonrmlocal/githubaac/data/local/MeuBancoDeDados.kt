package com.example.logonrmlocal.githubaac.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.logonrmlocal.githubaac.data.local.converter.DateConverter
import com.example.logonrmlocal.githubaac.data.local.dao.UserDAO
import com.example.logonrmlocal.githubaac.data.local.entity.User

@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MeuBancoDeDados: RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        private val INSTANCE : MeuBancoDeDados? = null
    }

}