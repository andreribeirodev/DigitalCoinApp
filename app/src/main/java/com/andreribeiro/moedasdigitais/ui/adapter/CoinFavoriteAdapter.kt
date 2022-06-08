package com.andreribeiro.moedasdigitais.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreribeiro.moedasdigitais.databinding.ItemCoinFavoriteBinding
import com.andreribeiro.moedasdigitais.db.entity.CoinEntity

class CoinFavoriteAdapter :
    ListAdapter<CoinEntity, CoinFavoriteAdapter.CoinFavoriteItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinFavoriteItemViewHolder {
        val binding =
            ItemCoinFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinFavoriteItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinFavoriteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CoinFavoriteItemViewHolder(
        private val binding: ItemCoinFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coinFavorite: CoinEntity) {
            binding.tvCoinFavoriteName.text = coinFavorite.name
            binding.tvCoinFavoriteNameAsset.text = coinFavorite.assetId
            binding.tvCoinFavoritePrice.text = coinFavorite.priceUsd.toString()
            // AQUI SERÁ O GLIDE PARA CARREGAMENTO DA IMAGEM
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinEntity>() {
            override fun areItemsTheSame(oldItem: CoinEntity, newItem: CoinEntity): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CoinEntity, newItem: CoinEntity): Boolean {
                return oldItem.assetId == newItem.assetId
            }
        }
    }
}
