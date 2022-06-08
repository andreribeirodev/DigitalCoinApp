package com.andreribeiro.moedasdigitais.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreribeiro.moedasdigitais.databinding.ItemCoinBinding
import com.andreribeiro.moedasdigitais.model.CoinModel
import com.bumptech.glide.Glide
import java.util.*

class CoinListAdapter :
    ListAdapter<CoinModel, CoinListAdapter.CoinItemViewHolder>(DIFF_CALLBACK),
    Filterable {

    var onClickListener: ((coinDetails: CoinModel) -> Unit)? = null
    private var list = mutableListOf<CoinModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: MutableList<CoinModel>?) {
        this.list = list!!
        submitList(list)
    }

    inner class CoinItemViewHolder(
        private val binding: ItemCoinBinding,
        private val onClickListener: ((coinDetails: CoinModel) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: CoinModel) {
            binding.textViewCoinName.text = coin.name
            binding.textViewCoinPrice.text = coin.priceUsd.toString()
            binding.textViewCoinSigla.text = coin.assetId

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
                return oldItem.assetId == newItem.assetId
            }
        }
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<CoinModel>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.name.lowercase(Locale.getDefault())
                        .startsWith(constraint.toString().lowercase(Locale.getDefault()))
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            submitList(results?.values as MutableList<CoinModel>)
        }
    }
}
