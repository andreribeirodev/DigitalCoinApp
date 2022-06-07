package com.andreribeiro.moedasdigitais.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreribeiro.moedasdigitais.db.datasource.ICoinFavoriteRepository
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity
import kotlinx.coroutines.launch

class CoinFavoriteFragmentViewModel(
    private val coinFavoriteRepository: ICoinFavoriteRepository
) : ViewModel() {

    private val _coinFavoriteList = MutableLiveData<List<CoinEntity>>()
    val coinFavoriteList: LiveData<List<CoinEntity>> = _coinFavoriteList

    fun addCoinFavorite(coinFavorite: CoinEntity) {
        viewModelScope.launch {
            coinFavoriteRepository.addCoinFavorite(coinFavorite)
        }
    }
}
