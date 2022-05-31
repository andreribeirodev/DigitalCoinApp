package com.andreribeiro.moedasdigitais.ui.listcoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.moedasdigitais.R
import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.CoinRepository
import com.andreribeiro.moedasdigitais.ui.adapter.AdapterListCoin
import com.andreribeiro.moedasdigitais.viewmodel.ListCoinViewModel

class ListCoinFragment : Fragment() {

    private val binding by lazy {
        FragmentListCoinBinding.inflate(layoutInflater)
    }
    private val adapterItemCoin = AdapterListCoin()

    private val coinService by lazy { CoinApiClient.coinService }
    private val coinRepository by lazy { CoinRepository(coinService) }
    private val listCoinFragmentFactory = ListCoinFactory(coinRepository)
    private val listCoinFragmentViewModel by viewModels<ListCoinViewModel> { listCoinFragmentFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerViewCoin.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCoin.adapter = adapterItemCoin
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
        onClickSetup()
    }

    private fun onClickSetup() {
        adapterItemCoin.onClickListener = {
            goToCoinDetails(it)
        }
    }

    private fun goToCoinDetails(coinDetails: CoinModel) {
        val bundle = bundleOf("coinDetails" to coinDetails)
        findNavController().navigate(R.id.action_listCoinFragment_to_detailsCoinFragment, bundle)
    }
}
