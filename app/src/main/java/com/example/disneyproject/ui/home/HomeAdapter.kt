package com.example.disneyproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.disneyproject.R
import com.example.disneyproject.databinding.HomeItemViewBinding
import com.example.disneyproject.models.Result
import com.example.disneyproject.utils.CellClickListener

class HomeAdapter(private val results: ArrayList<Result>,private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var  binding : HomeItemViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
           binding = HomeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        with(results[position]){
            val image = "${this.thumbnail.path}.${this.thumbnail.extension}"
            binding.comicTitle.text = this.title
            binding.issueNumber.text = this.issueNumber
            Glide.with(binding.comicImg.context)
                .load(image)
                .into(binding.comicImg)
            holder.itemView.setOnClickListener {
                cellClickListener.onCellClickListener(results[position])
            }
        }

    }

    override fun getItemCount() = results.size

    class HomeViewHolder(binding: HomeItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    fun addResults(results: ArrayList<Result>) {
        this.results.apply {
            clear()
            addAll(results)
        }
    }

}