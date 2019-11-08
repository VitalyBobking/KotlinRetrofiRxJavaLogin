package com.example.vitalii.kotlinretrofirxjavalogin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vitalii.kotlinretrofirxjavalogin.databinding.ProductBinding
import com.example.vitalii.kotlinretrofirxjavalogin.model.Product
import com.example.vitalii.kotlinretrofirxjavalogin.model.Review
import com.squareup.picasso.Picasso


class ProductAdapter(private val products: List<Product>, private val clickListener: CustomClickListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val binding = ProductBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(products[i])
        val count : Int? = products[i].id
        viewHolder.itemView.setOnClickListener{clickListener.cardClicked(count!!)}
    }
    override fun getItemCount(): Int {
        return products.size

    }
    inner class ViewHolder(private val binding: ProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.modelProduct = item
            binding.executePendingBindings()
        }
    }
    object DataBindingAdapter  {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            val urlPath = "http://smktesting.herokuapp.com/static/"
            Picasso.get().load(urlPath+url).into(view)
        }
    }
    fun addData(listItems: ArrayList<Review>) {
        val size = listItems.size
        listItems.addAll(listItems)
        val sizeNew = listItems.size
        notifyItemRangeChanged(size, sizeNew)
    }
}
