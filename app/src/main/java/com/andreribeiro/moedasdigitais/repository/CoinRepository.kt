package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.model.CoinModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinRepository(
    private val coinService: CoinApiClient
) : ICoinRepository {

    override suspend fun getCoinsByType(type: Int): List<CoinModel> {
        return withContext(Dispatchers.IO) {
            coinService.getCoinsByType().filter { it.type == type }
        }
    }
}
