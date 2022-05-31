package com.andreribeiro.moedasdigitais.ui.listcoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.CoinRepository
import com.andreribeiro.moedasdigitais.ui.adapter.AdapterListCoin
import com.andreribeiro.moedasdigitais.viewmodel.ListCoinViewModel

class ListCoinFragment : Fragment() {

    private var _binding: FragmentListCoinBinding? = null
    private val binding: FragmentListCoinBinding get() = _binding!!

    private val adapterItemCoin = AdapterListCoin()

    private val coinService by lazy { CoinApiClient.coinService }
    private val coinRepository by lazy { CoinRepository(coinService) }
    private val listCoinFragmentFactory = ListCoinFactory(coinRepository)
    private val listCoinFragmentViewModel by viewModels<ListCoinViewModel> { listCoinFragmentFactory }

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
        adapterConfig()
        getCoins()
    }

    private fun adapterConfig() {
        binding.recyclerViewCoin.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCoin.adapter = adapterItemCoin
    }

    private fun getCoins() {
        listCoinFragmentViewModel.getCoinList()
        listCoinFragmentViewModel.coinList.observe(this.viewLifecycleOwner) { coinList ->
            coinList.let {
                adapterItemCoin.submitList(coinList)
                onClickSetup()
            }
        }
    }

    private fun onClickSetup() {
        adapterItemCoin.onClickListener = { coinDetails ->
            goToFragmentDetails(coinDetails)
        }
    }

    private fun goToFragmentDetails(coinDetails: CoinModel) {
        val action =
            ListCoinFragmentDirections.actionListCoinFragmentToDetailsCoinFragment(coinDetails)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
