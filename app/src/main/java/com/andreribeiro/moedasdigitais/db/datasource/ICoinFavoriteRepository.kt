package com.andreribeiro.moedasdigitais.db.datasource

import androidx.lifecycle.LiveData
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

interface ICoinFavoriteRepository {

    suspend fun addCoinFavorite(coinFavorite: CoinEntity)

    suspend fun updateCoinFavorite(coinFavorite: CoinEntity)

    suspend fun deleteCoinFavorite(coinFavorite: CoinEntity)

    suspend fun deleteAllCoinsFavorite()

    fun readAllCoinsFavorite(): LiveData<List<CoinEntity>>
}
