package com.andreribeiro.moedasdigitais.ui.listcoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.CoinRepository
import com.andreribeiro.moedasdigitais.ui.adapter.CoinListAdapter
import com.andreribeiro.moedasdigitais.viewmodel.CoinListFragmentViewModel

class CoinListFragment : Fragment() {

    private var _binding: FragmentListCoinBinding? = null
    private val binding: FragmentListCoinBinding get() = _binding!!
    private val adapterItemCoin = CoinListAdapter()

    private val coinService by lazy { CoinApiClient.coinService }
    private val coinRepository by lazy { CoinRepository(coinService) }
    private val listCoinFragmentFactory = CoinListFactory(coinRepository)
    private val listCoinFragmentViewModel by viewModels<CoinListFragmentViewModel> { listCoinFragmentFactory }

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
        searchViewSetup()
        onClickSetup()
    }

    private fun searchViewSetup() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterItemCoin.filter.filter(newText)
                return true
            }
        })
    }

    private fun adapterConfig() {
        binding.recyclerViewCoin.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCoin.adapter = adapterItemCoin
    }

    private fun getCoins() {
        listCoinFragmentViewModel.getCoinList()
        listCoinFragmentViewModel.coinList.observe(viewLifecycleOwner) { coinList ->
            adapterItemCoin.setData(coinList.toMutableList())
        }
    }

    private fun onClickSetup() {
        adapterItemCoin.onClickListener = { coinDetails ->
            goToFragmentDetails(coinDetails)
        }
    }

    private fun goToFragmentDetails(coinDetails: CoinModel) {
        val action =
            CoinListFragmentDirections.actionListCoinFragmentToDetailsCoinFragment(coinDetails)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
