package com.andreribeiro.moedasdigitais.db.datasource

import androidx.lifecycle.LiveData
import com.andreribeiro.moedasdigitais.db.dao.CoinDao
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

class CoinFavoriteRepositoryImpl(
    private val coinDao: CoinDao
) : ICoinFavoriteRepository {

    val readAllData: LiveData<List<CoinEntity>> = readAllCoinsFavorite()

    override suspend fun addCoinFavorite(coinFavorite: CoinEntity) {
        coinDao.addCoinFavorite(coinFavorite)
    }

    override suspend fun updateCoinFavorite(coinFavorite: CoinEntity) {
        coinDao.updateCoinFavorite(coinFavorite)
    }

    override suspend fun deleteCoinFavorite(coinFavorite: CoinEntity) {
        coinDao.deleteCoinFavorite(coinFavorite)
    }

    override suspend fun deleteAllCoinsFavorite() {
        coinDao.deleteAllCoinsFavorite()
    }

    override fun readAllCoinsFavorite(): LiveData<List<CoinEntity>> {
        return coinDao.readAllCoinsFavorite()
    }
}
