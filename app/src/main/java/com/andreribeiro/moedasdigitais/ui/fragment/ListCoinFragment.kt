package com.andreribeiro.moedasdigitais.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.repository.CoinRepository
import com.andreribeiro.moedasdigitais.ui.ListCoinFragmentFactory
import com.andreribeiro.moedasdigitais.ui.adapter.AdapterListCoin
import com.andreribeiro.moedasdigitais.viewmodel.ListCoinFragmentViewModel

class ListCoinFragment : Fragment() {

    private val binding by lazy {
        FragmentListCoinBinding.inflate(layoutInflater)
    }
    private val adapterItemCoin = AdapterListCoin()

    private val coinService by lazy { CoinApiClient.coinService }
    private val coinRepository by lazy { CoinRepository(coinService) }
    private val listCoinFragmentFactory = ListCoinFragmentFactory(coinRepository)
    private val listCoinFragmentViewModel by viewModels<ListCoinFragmentViewModel> { listCoinFragmentFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerViewCoin.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCoin.adapter = adapterItemCoin

        adapterOnClickSetup()
        getCoins()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun getCoins() {
        listCoinFragmentViewModel.getCoinList()
        listCoinFragmentViewModel.coinList.observe(this) { coinList ->
            coinList.let {
                adapterItemCoin.submitList(coinList)
            }
        }
    }

    private fun adapterOnClickSetup() {
        adapterItemCoin.onClickListener = { coinName ->
            Toast.makeText(context, "Moeda selecionada: $coinName", Toast.LENGTH_SHORT).show()
        }
    }
}
