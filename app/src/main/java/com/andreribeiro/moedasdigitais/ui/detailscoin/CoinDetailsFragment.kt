package com.andreribeiro.moedasdigitais.ui.detailscoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andreribeiro.moedasdigitais.databinding.FragmentDetailsCoinBinding
import com.bumptech.glide.Glide

class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentDetailsCoinBinding? = null
    private val binding: FragmentDetailsCoinBinding get() = _binding!!

    private val args by navArgs<CoinDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsCoinBinding.inflate(inflater, container, false)
        return binding.root
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
        binding.textViewCoinAbreviation.text = details.Id

        Glide.with(this)
            .load(details.cryptoImage())
            .into(binding.imageViewCoin)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
