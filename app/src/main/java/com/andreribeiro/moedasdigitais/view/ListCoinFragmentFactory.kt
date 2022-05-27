package com.andreribeiro.moedasdigitais.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andreribeiro.moedasdigitais.repository.ICoinRepository
import com.andreribeiro.moedasdigitais.viewmodel.ListCoinFragmentViewModel

class ListCoinFragmentFactory(
    private val coinRepository: ICoinRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListCoinFragmentViewModel(coinRepository) as T
    }
}
