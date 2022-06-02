package com.andreribeiro.moedasdigitais.api

import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.util.Constants.BASE_URL
import com.andreribeiro.moedasdigitais.util.Constants.ENDPOINT_ASSETS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CoinApiClient {

    @GET(ENDPOINT_ASSETS)
    suspend fun getCoinsByType(): List<CoinModel>

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val coinService = retrofit.create(CoinApiClient::class.java)
    }
}
