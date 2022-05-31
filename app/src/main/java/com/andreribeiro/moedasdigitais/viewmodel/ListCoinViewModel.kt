package com.andreribeiro.moedasdigitais.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.ICoinRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCoinViewModel(
    private val coinRepository: ICoinRepository
) : ViewModel() {

    private val _coins = MutableLiveData<List<CoinModel>>()
    val coinList: LiveData<List<CoinModel>> = _coins

    fun getCoinList() {
        coinRepository.getCoins().enqueue(object : Callback<List<CoinModel>> {
            override fun onResponse(
                call: Call<List<CoinModel>>,
                response: Response<List<CoinModel>>
            ) {
                val coinList = response.body()
                _coins.postValue(coinList?.filter { it.type == 1 })
            }

            override fun onFailure(call: Call<List<CoinModel>>, t: Throwable) {
                println("Error: $t")
            }
        })
    }
}
