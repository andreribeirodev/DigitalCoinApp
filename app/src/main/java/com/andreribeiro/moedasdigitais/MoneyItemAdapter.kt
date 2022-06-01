package com.andreribeiro.moedasdigitais

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andreribeiro.moedasdigitais.databinding.ActivityListItemBinding
import com.bumptech.glide.Glide

class MoneyItemAdapter :
    ListAdapter<Result, MoneyItemAdapter.MoneyItemViewHolder>(DIFF_CALLBACK) {

    private var onClickListener: ((filmId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyItemViewHolder {
        val binding =
            ActivityListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoneyItemViewHolder(binding, onClickListener)

    }

    override fun onBindViewHolder(holder: MoneyItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MoneyItemViewHolder(
        private val binding: ActivityListItemBinding,
        private val onClickListener: ((filmId: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(film: Result) {

            binding.moneyTitle.text = film.title

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original" + film.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(binding.moneySymbol)

            binding.moneyAcronym.text = "Lançamento: ${film.release_date}"

            binding.moneyValor.text = "Classificação: ${film.vote_average}"

            binding.root.setOnClickListener {
                onClickListener?.invoke(film.id)
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}