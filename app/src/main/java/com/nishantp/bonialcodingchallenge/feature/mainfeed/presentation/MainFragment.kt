package com.nishantp.bonialcodingchallenge.feature.mainfeed.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nishantp.bonialcodingchallenge.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedAdapter = FeedAdapter()
        binding.feedRecyclerView.adapter = feedAdapter

        viewModel.feedState.observe(viewLifecycleOwner) { state ->
            binding.progressBar.isVisible = state.isLoading
            if (state.isError) {
                Toast.makeText(
                    requireContext(),
                    state.error?.asString(requireContext()),
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (state.brochures.isNotEmpty()) {
                feedAdapter.submitList(state.brochures)
            }
        }
    }

}