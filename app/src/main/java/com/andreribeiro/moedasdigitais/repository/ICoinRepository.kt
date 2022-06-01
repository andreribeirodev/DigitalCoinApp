package com.andreribeiro.moedasdigitais.repository

import com.andreribeiro.moedasdigitais.model.CoinModel

interface ICoinRepository {
    suspend fun getCoin(): List<CoinModel>
}
