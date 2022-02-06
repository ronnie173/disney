package com.example.disneyproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disneyproject.R
import com.example.disneyproject.databinding.HomeLayoutBinding
import com.example.disneyproject.models.ComicsModel
import com.example.disneyproject.models.Result
import com.example.disneyproject.ui.homedetail.HomeDetailFragmentDirections
import com.example.disneyproject.utils.CellClickListener
import com.example.disneyproject.utils.Resource
import com.example.disneyproject.utils.Status
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(), CellClickListener {

    private var _binding: HomeLayoutBinding? = null
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var adapter: HomeAdapter
    private var results = arrayListOf<Result>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            HomeLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    /**
     * On view created
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        viewModel.getUsers().observe(viewLifecycleOwner) { data ->
            handleData(data)
        }

    }

    /**
     * Handle data
     *
     * @param data
     */
    private fun handleData(data: Resource<ComicsModel>) {
        results.clear()
        data.let { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding.circularProgressIndicator.visibility = INVISIBLE
                    resource.data!!.data.results.forEach { result ->
                        results.add(result)
                    }
                    results.sortBy { it.id }
                    adapter.addResults(results)
                    adapter.notifyDataSetChanged()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Set up ui
     *
     */
    private fun setUpUi() {
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter( this)
        binding.homeRecyclerView.adapter = adapter
    }

    /**
     * On cell click listener
     *
     * @param result
     */
    override fun onCellClickListener(result: Result) {
        val imageUrl = "${result.thumbnail.path}.${result.thumbnail.extension}"
        val description = result.description
        val title = result.title
        val readMoreUri = result.urls.first().url
        val action = HomeFragmentDirections.actionHomeToDetail(imageUrl,description,title,readMoreUri)
        findNavController().navigate(action)
    }
}