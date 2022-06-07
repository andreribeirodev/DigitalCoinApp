package com.andreribeiro.moedasdigitais.db.datasource

import androidx.lifecycle.LiveData
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

interface ICoinFavoriteRepository {

    suspend fun getAllCoinFavorite(): LiveData<List<CoinEntity>>

    suspend fun addCoinFavorite(coinFavorite: CoinEntity)

    suspend fun deleteCoinFavorite(coinId: Int)
}
