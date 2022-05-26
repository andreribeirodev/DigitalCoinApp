package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.model.CoinModel
import retrofit2.Call

class CoinRepository(
    private val coinService: CoinApiClient
) : ICoinRepository {

    override fun getCoins(): Call<List<CoinModel>> {
        return coinService.getCoins()
    }
}
