package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.model.CoinModel

interface ICoinRepository {
    suspend fun getCoin(type: Int): List<CoinModel>
}
