package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.model.CoinModel

interface ICoinRepository {
    suspend fun getCoinsByType(type: Int): List<CoinModel>
}
