package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.model.CoinModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinRepository(
    private val coinService: CoinApiClient
) : ICoinRepository {

    override suspend fun getCoin(type: Int): List<CoinModel> {
        return withContext(Dispatchers.IO) {
            coinService.getCoins().filter { it.type == type }
        }
    }
}
