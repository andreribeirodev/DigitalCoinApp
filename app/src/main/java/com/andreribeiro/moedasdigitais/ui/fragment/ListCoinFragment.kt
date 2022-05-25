package com.andreribeiro.moedasdigitais.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreribeiro.moedasdigitais.databinding.FragmentListCoinBinding
import com.andreribeiro.moedasdigitais.model.mockCoin
import com.andreribeiro.moedasdigitais.ui.adapter.AdapterItemCoin

class ListCoinFragment : Fragment() {

    private val binding by lazy {
        FragmentListCoinBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapterItemCoin = AdapterItemCoin()
        binding.recyclerViewCoin.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCoin.adapter = adapterItemCoin

        adapterItemCoin.onClickListener = { coinName ->
            Toast.makeText(context, "Moeda selecionada: $coinName", Toast.LENGTH_SHORT).show()
        }

        adapterItemCoin.submitList(mockCoin())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}
