package com.andreribeiro.moedasdigitais.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.ICoinRepository
import com.andreribeiro.moedasdigitais.util.Constants.IS_CRYPTO
import kotlinx.coroutines.launch

class ListCoinFragmentViewModel(
    private val coinRepository: ICoinRepository
) : ViewModel() {

    private val _coins = MutableLiveData<List<CoinModel>>()
    val coinList: LiveData<List<CoinModel>> = _coins

    fun getCoinList() {
        viewModelScope.launch {
            val coinList = coinRepository.getCoin(IS_CRYPTO)
            _coins.value = coinList
        }
    }
}
