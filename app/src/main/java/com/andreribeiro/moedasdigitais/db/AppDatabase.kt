package com.andreribeiro.moedasdigitais.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andreribeiro.moedasdigitais.db.dao.CoinDao
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    companion object {
        private const val DATABASE_NAME = "CoinsFavorites"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).build()
    }
}
