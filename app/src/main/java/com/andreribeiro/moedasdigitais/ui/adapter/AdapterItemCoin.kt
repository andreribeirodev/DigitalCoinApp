package com.andreribeiro.moedasdigitais.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreribeiro.moedasdigitais.databinding.ItemCoinBinding
import com.andreribeiro.moedasdigitais.model.Coin

class AdapterItemCoin : ListAdapter<Coin, AdapterItemCoin.CoinItemViewHolder>(DIFF_CALLBACK) {

    var onClickListener: ((coinName: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CoinItemViewHolder(
        private val binding: ItemCoinBinding,
        private val onClickListener: ((coinName: String) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) {
            binding.textViewCoinName.text = coin.name
            binding.textViewCoinPrice.text = coin.price
            binding.textViewCoinSigla.text = coin.sigla

            binding.root.setOnClickListener {
                onClickListener?.invoke(coin.name)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Coin>() {

            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem == newItem
            }
        }
    }
}
