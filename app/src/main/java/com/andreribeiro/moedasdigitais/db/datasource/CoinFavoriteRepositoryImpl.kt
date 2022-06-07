package com.andreribeiro.moedasdigitais.db.datasource

import androidx.lifecycle.LiveData
import com.andreribeiro.moedasdigitais.db.AppDatabase
import com.andreribeiro.moedasdigitais.db.dao.CoinDao
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinFavoriteRepositoryImpl(
    private val appDatabase: CoinDao
) : ICoinFavoriteRepository {

    override suspend fun getAllCoinFavorite(): LiveData<List<CoinEntity>> {
        return withContext(Dispatchers.IO) {
            appDatabase.CoinDao().getAllCoins()
        }
    }

    override suspend fun addCoinFavorite(coinFavorite: CoinEntity) {
        withContext(Dispatchers.IO) {
            appDatabase.CoinDao().coinSave(coinFavorite)
        }
    }

    override suspend fun deleteCoinFavorite(coinId: Int) {
        withContext(Dispatchers.IO) {
            appDatabase.CoinDao().delete(coinId)
        }
    }
}
