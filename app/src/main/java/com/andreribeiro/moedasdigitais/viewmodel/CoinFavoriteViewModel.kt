package com.andreribeiro.moedasdigitais.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.andreribeiro.moedasdigitais.db.AppDatabase
import com.andreribeiro.moedasdigitais.db.datasource.CoinFavoriteRepositoryImpl
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinFavoriteViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<CoinEntity>>
    private val repository: CoinFavoriteRepositoryImpl

    init {
        val coinDao = AppDatabase.getInstance(
            application
        ).coinDao()
        repository = CoinFavoriteRepositoryImpl(coinDao)
        readAllData = repository.readAllData
    }

    fun addCoinFavorite(coinFavorite: CoinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCoinFavorite(coinFavorite)
        }
    }

    fun updateCoinFavorite(coinFavorite: CoinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCoinFavorite(coinFavorite)
        }
    }

    fun deleteCoinFavorite(coinFavorite: CoinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCoinFavorite(coinFavorite)
        }
    }

    fun deleteAllCoinsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCoinsFavorite()
        }
    }
}
