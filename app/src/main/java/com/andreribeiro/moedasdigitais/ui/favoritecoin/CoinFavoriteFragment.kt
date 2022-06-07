package com.andreribeiro.moedasdigitais.ui.favoritecoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.db.AppDatabase
import com.andreribeiro.moedasdigitais.db.dao.CoinDao
import com.andreribeiro.moedasdigitais.db.datasource.CoinFavoriteRepositoryImpl
import com.andreribeiro.moedasdigitais.db.datasource.ICoinFavoriteRepository
import com.andreribeiro.moedasdigitais.viewmodel.CoinFavoriteFragmentViewModel

class FavoriteCoinFragment : Fragment() {

    private var _binding: FragmentListCoinBinding? = null
    private val binding: FragmentListCoinBinding get() = _binding!!

    private val viewModel: CoinFavoriteFragmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val coinDao: CoinDao = AppDatabase.getInstance(requireContext()).CoinDao()

                val coinFavoriteRepository: ICoinFavoriteRepository =
                    CoinFavoriteRepositoryImpl(coinDao)
                return CoinFavoriteFragmentViewModel(coinFavoriteRepository) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
