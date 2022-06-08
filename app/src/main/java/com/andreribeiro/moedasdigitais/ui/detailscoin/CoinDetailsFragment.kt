package com.andreribeiro.moedasdigitais.ui.detailscoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreribeiro.moedasdigitais.R
import com.andreribeiro.moedasdigitais.databinding.FragmentDetailsCoinBinding
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity
import com.andreribeiro.moedasdigitais.viewmodel.CoinFavoriteViewModel
import com.bumptech.glide.Glide

class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentDetailsCoinBinding? = null
    private val binding: FragmentDetailsCoinBinding get() = _binding!!

    private val args by navArgs<CoinDetailsFragmentArgs>()

    private lateinit var mCoinFavoriteViewModel: CoinFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsCoinBinding.inflate(inflater, container, false)

        mCoinFavoriteViewModel = ViewModelProvider(this).get(CoinFavoriteViewModel::class.java)

        binding.btnAddFavorite.setOnClickListener {
            insertDataToDataBase()
        }

        return binding.root
    }

    private fun insertDataToDataBase() {
        val coinFavorite = setupCoinFavoriteData()
        mCoinFavoriteViewModel.addCoinFavorite(coinFavorite)
        Toast.makeText(requireContext(), "Sucess", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_detailsCoinFragment_to_listCoinFragment)
    }

    private fun setupCoinFavoriteData(): CoinEntity {
        val coinDetails = args.coinDetails
        return CoinEntity(
            id = 0,
            assetId = coinDetails.assetId,
            name = coinDetails.name,
            volumeHrsUsd = coinDetails.volumeHrsUsd,
            volumeDayUsd = coinDetails.volumeDayUsd,
            volumeMthUsd = coinDetails.volumeMthUsd,
            priceUsd = coinDetails.priceUsd,
            iconId = coinDetails.iconId!!
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetailsAndPopulateViews()
    }

    private fun getDetailsAndPopulateViews() {
        val details = args.coinDetails
        binding.textViewPriceCoin.text = details.priceUsd.toString()
        binding.textViewPriceHours.text = details.volumeHrsUsd.toString()
        binding.textViewPriceDay.text = details.volumeDayUsd.toString()
        binding.textViewPriceMonth.text = details.volumeMthUsd.toString()
        binding.textViewCoinAbreviation.text = details.assetId

        Glide.with(this)
            .load(details.cryptoImage())
            .into(binding.imageViewCoin)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
