package com.andreribeiro.moedasdigitais.ui.listcoin

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.moedasdigitais.api.CoinApiClient
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.andreribeiro.moedasdigitais.repository.CoinRepository
import com.andreribeiro.moedasdigitais.ui.adapter.AdapterListCoin
import com.andreribeiro.moedasdigitais.util.Resource
import com.andreribeiro.moedasdigitais.viewmodel.ListCoinFragmentViewModel

class ListCoinFragment : Fragment() {

    private var _binding: FragmentListCoinBinding? = null
    private val binding: FragmentListCoinBinding get() = _binding!!

    private val adapterItemCoin = AdapterListCoin()

    private val coinService by lazy { CoinApiClient.coinService }
    private val coinRepository by lazy { CoinRepository(coinService) }
    private val listCoinFragmentFactory = ListCoinFactory(coinRepository)
    private val listCoinFragmentViewModel by viewModels<ListCoinFragmentViewModel> { listCoinFragmentFactory }

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
        listCoinFragmentViewModel.coins.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("INFO", "Loading")
                }
                is Resource.Sucess -> {
                    adapterItemCoin.setData(resource.data.toMutableList())
                    Log.d("INFO", "Sucess")
                }
                is Resource.Error -> {
                    alertError()
                    Log.d("INFO", "Error: ${resource.throwable.message}")
                }
            }
        }
        onClickSetup()
    }

    fun alertError() {
        var alertDialog: AlertDialog = AlertDialog.Builder(context) // set icon
            .setIcon(R.drawable.ic_dialog_alert)
            .setTitle("Ocorreu um erro: ")
            .setMessage("Deseja tentar novamente?")
            .setPositiveButton(
                "Tentar novamente",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    getCoins()
                }
            )
            .setNegativeButton(
                "Sair",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                    Toast.makeText(context, "Tente novamente mais tarde.", Toast.LENGTH_LONG).show()
                }
            )
            .show()
    }
//    private fun getCoins() {
//        listCoinFragmentViewModel.getCoinList()
//        listCoinFragmentViewModel.coinList.observe(viewLifecycleOwner) { coinList ->
//            coinList.let {
//                adapterItemCoin.setData(coinList.toMutableList())
//            }
//        }
//        onClickSetup()
//    }

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
