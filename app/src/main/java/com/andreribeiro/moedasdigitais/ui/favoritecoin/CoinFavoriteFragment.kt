package com.andreribeiro.moedasdigitais.ui.favoritecoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.andreribeiro.moedasdigitais.databinding.FragmentFavoriteCoinBinding
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity
import com.andreribeiro.moedasdigitais.ui.adapter.CoinFavoriteAdapter
import com.andreribeiro.moedasdigitais.viewmodel.CoinFavoriteDatabaseViewModel

class CoinFavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteCoinBinding? = null
    private val binding: FragmentFavoriteCoinBinding get() = _binding!!

    private val coinFavAdapter by lazy { CoinFavoriteAdapter() }

    private lateinit var mFavoriteViewModel: CoinFavoriteDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteCoinBinding.inflate(inflater, container, false)

        // viewModel
        mFavoriteViewModel = ViewModelProvider(this).get(CoinFavoriteDatabaseViewModel::class.java)

        // RecyclerView
        setupRecyclerFavorite()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupRecyclerFavorite() {
        binding.rvCoinFavorite.adapter = coinFavAdapter
        binding.rvCoinFavorite.setHasFixedSize(true)
        binding.rvCoinFavorite.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setupObservers() {
        mFavoriteViewModel.readAllData.observe(viewLifecycleOwner) { coinFavList ->
            setupPopulateAdapter(coinFavList)
        }
    }

    private fun setupPopulateAdapter(coinFavorite: List<CoinEntity>) {
        coinFavAdapter.submitList(coinFavorite)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
