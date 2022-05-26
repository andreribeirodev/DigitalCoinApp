package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.model.CoinModel
import retrofit2.Call

interface ICoinRepository {
    fun getCoins(): Call<List<CoinModel>>
}
