package com.fucukur.moviedbapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.fucukur.moviedbapp.base.BaseFragment
import com.fucukur.moviedbapp.databinding.MovieDetailFragmentBinding

class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding>(MovieDetailFragmentBinding::inflate) {
    private val args : MovieDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        backPressCallBack()
    }

    private fun setData() {
        val data = args.movieId
        Log.e("Moview List Log -> ", data.toString())
        binding.tvRelease.text = data?.realease
        binding.tvMovieTitle.text = data?.title
        binding.tvDescripton.text = data?.overview
        binding.sdvItemImage.load(data?.backDrops)
        binding.tvVote.text = data?.voteAverage.toString()
    }

    private fun backPressCallBack() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}
