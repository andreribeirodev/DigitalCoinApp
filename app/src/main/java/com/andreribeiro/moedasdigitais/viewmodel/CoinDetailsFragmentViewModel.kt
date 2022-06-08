package com.andreribeiro.moedasdigitais.viewmodel

import androidx.lifecycle.ViewModel
import com.andreribeiro.moedasdigitais.db.datasource.ICoinFavoriteRepository

class CoinDetailsFragmentViewModel(
    private val coinFavoriteRepository: ICoinFavoriteRepository
) : ViewModel()
