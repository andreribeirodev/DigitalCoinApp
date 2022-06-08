package com.andreribeiro.moedasdigitais.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoinFavorite(coinFavorite: CoinEntity)

    @Update
    suspend fun updateCoinFavorite(coinFavorite: CoinEntity)

    @Delete
    suspend fun deleteCoinFavorite(coinFavorite: CoinEntity)

    @Query("DELETE FROM coins")
    suspend fun deleteAllCoinsFavorite()

    @Query("SELECT * FROM coins ORDER BY id ASC")
    fun readAllCoinsFavorite(): LiveData<List<CoinEntity>>
}
