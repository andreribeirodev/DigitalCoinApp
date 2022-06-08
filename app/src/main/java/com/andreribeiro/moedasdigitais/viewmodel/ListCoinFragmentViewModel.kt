package com.andreribeiro.moedasdigitais.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.ICoinRepository
import com.andreribeiro.moedasdigitais.util.Constants.IS_CRYPTO
import com.andreribeiro.moedasdigitais.util.Resource
import kotlinx.coroutines.launch

class ListCoinFragmentViewModel(
    private val coinRepository: ICoinRepository
) : ViewModel() {

    private val _coins = MutableLiveData<Resource<List<CoinModel>>>()
    val coins: LiveData<Resource<List<CoinModel>>> = _coins
    fun getCoinList() {
        viewModelScope.launch {
            _coins.value = Resource.Loading()
            try {
                val coinList = coinRepository.getCoin(IS_CRYPTO)
                _coins.value = Resource.Sucess(data = coinList)
            } catch (exception: Exception) {
                val coinResource = Resource.Error<List<CoinModel>>(exception)
                _coins.value = coinResource
            }
        }
    }
}
