package com.example.vitalii.kotlinretrofirxjavalogin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.vitalii.kotlinretrofirxjavalogin.databinding.ReviewBinding
import com.example.vitalii.kotlinretrofirxjavalogin.model.Review


class ReviewAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val binding = ReviewBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
       viewHolder.bind(reviews[i])
    }
    override fun getItemCount(): Int {
        return reviews.size

    }
    inner class ViewHolder(private val binding: ReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Review) {
            binding.review = item
            binding.executePendingBindings()
        }
    }
}