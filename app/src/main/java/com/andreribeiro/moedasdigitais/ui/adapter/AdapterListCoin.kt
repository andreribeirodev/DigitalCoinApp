package com.andreribeiro.moedasdigitais.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreribeiro.moedasdigitais.databinding.ItemCoinBinding
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.bumptech.glide.Glide

class AdapterListCoin : ListAdapter<CoinModel, AdapterListCoin.CoinItemViewHolder>(DIFF_CALLBACK) {

    var onClickListener: ((coinDetails: CoinModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CoinItemViewHolder(
        private val binding: ItemCoinBinding,
        private val onClickListener: ((coinDetails: CoinModel) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: CoinModel) {
            binding.textViewCoinName.text = coin.name
            binding.textViewCoinPrice.text = coin.priceUsd.toString()
            binding.textViewCoinSigla.text = coin.Id

            Glide.with(binding.root.context)
                .load(coin.cryptoImage())
                .centerCrop()
                .into(binding.imageViewCoin)

            binding.root.setOnClickListener {
                onClickListener?.invoke(coin)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinModel>() {

            override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
