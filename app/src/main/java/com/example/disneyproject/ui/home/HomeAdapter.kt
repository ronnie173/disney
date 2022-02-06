package com.example.disneyproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.disneyproject.databinding.HomeItemViewBinding
import com.example.disneyproject.models.Result
import com.example.disneyproject.utils.CellClickListener

/**
 * Home adapter
 *
 * @property cellClickListener
 * @constructor Home adapter
 */
class HomeAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var binding: HomeItemViewBinding
    var results: ArrayList<Result> = arrayListOf()

    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = HomeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    /**
     * On bind view holder
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        with(results[position]) {
            val image = "${this.thumbnail.path}.${this.thumbnail.extension}"
            binding.comicTitle.text = this.title
            Glide.with(binding.comicImg.context)
                .load(image)
                .into(binding.comicImg)
            holder.itemView.setOnClickListener {
                cellClickListener.onCellClickListener(results[position])
            }
        }

    }

    /**
     * Get item id
     *
     * @param position
     * @return
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Get item view type
     *
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        return position
    }

    /**
     * Get item count
     *
     */
    override fun getItemCount() = results.size

    /**
     * Home view holder
     *
     * @constructor
     *
     * @param binding
     */
    class HomeViewHolder(binding: HomeItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Add results
     *
     * @param results
     */
    fun addResults(results: ArrayList<Result>) {
        this.results.apply {
            clear()
            addAll(results)
        }
    }

}