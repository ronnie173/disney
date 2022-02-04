package com.example.disneyproject.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disneyproject.R
import com.example.disneyproject.databinding.HomeLayoutBinding
import com.example.disneyproject.models.Result
import com.example.disneyproject.utils.Status
import com.google.android.material.progressindicator.CircularProgressIndicator
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: HomeLayoutBinding? = null
    val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var adapter: HomeAdapter
    private var results = arrayListOf<Result>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            HomeLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUi(results: ArrayList<Result>) {
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter(results)
        binding.homeRecyclerView.adapter = adapter

    }

    private fun setupObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner) { data ->
            data?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                      //  binding.circularProgressIndicator.set
                        resource.data!!.data.results.forEach { result ->
                            results.add(result)
                        }
                        setUpUi(results)
                    }
                    Status.ERROR -> {
                        Timber.d("")

                    }
                    Status.LOADING -> {
                        Timber.d("")
                         binding.circularProgressIndicator.show()
                    }
                }
            }
        }
    }
}