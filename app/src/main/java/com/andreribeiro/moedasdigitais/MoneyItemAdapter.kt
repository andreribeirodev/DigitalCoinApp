package com.andreribeiro.moedasdigitais

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MoneyItemAdapter :
    ListAdapter<Result, MoneyItemAdapter.FilmItemViewHolder>(DIFF_CALLBACK) {

    private var onClickListener: ((filmId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val binding =
            FilmListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmItemViewHolder(binding, onClickListener)

    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FilmItemViewHolder(
        private val binding: FilmListItemBinding,
        private val onClickListener: ((filmId: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(film: Result) {

            binding.tvMovieTitle.text = film.title

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original" + film.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(binding.ivMovie)

            binding.tvReleaseDate.text = "Lançamento: ${film.release_date.getDateTimeFormatted()}"

            binding.tvVoteAverage.text = "Classificação: ${film.vote_average}"

            binding.root.setOnClickListener {
                onClickListener?.invoke(film.id)
            }
        }

        private fun String.getDateTimeFormatted(): String {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", getLocale())
                val date = dateFormat.parse(this)
                date?.let {
                    return getDateToStringFormatted(date, "dd/MM/yyyy")
                }
            } catch (e: ParseException) {
                e.localizedMessage?.let {
                    Log.d("TAG", "getDateTimeFormatted: $e")
                }
            }
            return orEmpty()
        }

        private fun getDateToStringFormatted(date: Date, dateString: String): String {
            val simpleDateFormat = SimpleDateFormat(dateString, getLocale())
            return simpleDateFormat.format(date)
        }

        private fun getLocale(): Locale {
            return Locale("pt", "BR")
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