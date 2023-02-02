package com.fucukur.moviedbapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.fucukur.moviedbapp.R
import com.fucukur.moviedbapp.base.BaseFragment
import com.fucukur.moviedbapp.databinding.FragmentMainMovieBinding
import com.fucukur.moviedbapp.ui.adapter.MovieAdapter
import com.fucukur.moviedbapp.ui.state.MainState
import com.fucukur.moviedbapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment :
    BaseFragment<FragmentMainMovieBinding>(FragmentMainMovieBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val imageList = ArrayList<SlideModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initShimmer()
        initAdapter()
        initNowPlayingData()
        initComingData()
        initNav()
        initSlider()
        initLoading()
    }



    private fun initAdapter() {
        binding.rvMovieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initNowPlayingData() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.nowPlaying.collect { nowplaying ->
                when {
                    nowplaying.isLoading -> {
                        //showProgressBar()
                        initShimmer()
                    }

                    nowplaying.error.isNotEmpty() -> {
                        Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
                    }

                    nowplaying.data?.isNotEmpty() == true -> {
                        hideProgressBar()
                        stopShimmer()
                        setImage(nowplaying)

                    }
                }
            }
        }
    }

    private fun initComingData() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.upComing.collect { upcoming ->
                if (upcoming.isLoading) {
                    //showProgressBar()
                    initShimmer()
                }
                if (upcoming.error.isNotBlank()) {
                    Toast.makeText(context, "Check Connect", Toast.LENGTH_LONG).show()
                }

                upcoming.data?.let {
                    stopShimmer()
                    hideProgressBar()
                    movieAdapter.movies = it
                }
            }
        }
    }

    private fun initNav() {
        movieAdapter.setOnItemClickListener {
            val action =
                MovieFragmentDirections.actionMainMovieFragmentToDetailMovieFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }

    private fun initSlider() {
        binding.isImageView.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                movieAdapter.setOnItemClickListener {
                    val action =
                        MovieFragmentDirections.actionMainMovieFragmentToDetailMovieFragment(it)
                    findNavController().navigate(action)
                }
            }
        })
    }

    private fun initLoading() {
        binding.srlRefreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.srlRefreshLayout.setColorSchemeColors(Color.RED)

        binding.srlRefreshLayout.setOnRefreshListener {
            initComingData()
            binding.srlRefreshLayout.isRefreshing = false
        }
    }

    private fun setImage(nowPlaying: MainState) {
        imageList.add(
            SlideModel(
                nowPlaying.data?.get(0)?.slider,
                nowPlaying.data?.get(0)?.title, ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                nowPlaying.data?.get(1)?.slider,
                nowPlaying.data?.get(1)?.title, ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                nowPlaying.data?.get(2)?.slider,
                nowPlaying.data?.get(2)?.title, ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                nowPlaying.data?.get(3)?.slider,
                nowPlaying.data?.get(3)?.title, ScaleTypes.CENTER_CROP
            )
        )
        binding.isImageView.setImageList(imageList)
    }

    private fun hideProgressBar() {
        binding.pbBar.visibility = View.INVISIBLE
    }

    private fun initShimmer() {
        binding.sftShimmer.startShimmer()
        binding.sftShimmer.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.pbBar.visibility = View.VISIBLE
    }

    private fun stopShimmer(){
        binding.sftShimmer.stopShimmer()
        binding.sftShimmer.visibility = View.GONE
    }
}
