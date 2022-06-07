package com.andreribeiro.moedasdigitais.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins")
    fun getAllCoins(): LiveData<List<CoinEntity>>

    @Insert
    suspend fun coinSave(coinFavorite: CoinEntity)

    @Delete
    suspend fun delete(coinId: Int)
}
