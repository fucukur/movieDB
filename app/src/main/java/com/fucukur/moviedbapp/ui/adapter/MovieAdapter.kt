package com.fucukur.moviedbapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fucukur.moviedbapp.databinding.MainItemRowBinding
import com.fucukur.moviedbapp.domain.model.NetworkMovie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MainViewHolder>() {
    inner class MainViewHolder(private val binding: MainItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: NetworkMovie) {
            binding.apply {
                Log.e("poster url -> ", movie.posterUrl)
                imgMovie.load(movie.posterUrl)
                Log.e("movie title -> ", movie.title)
                tvMovieTitle.text = movie.title
                tvDescription.text = movie.overview
                tvRealease.text = movie.realease
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    private var onItemClickListener: ((NetworkMovie) -> Unit)? = null

    fun setOnItemClickListener(listener: (NetworkMovie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainItemRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<NetworkMovie>() {
        override fun areItemsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var movies: List<NetworkMovie>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movieItem = movies[position]
        holder.bind(movieItem)
    }
}