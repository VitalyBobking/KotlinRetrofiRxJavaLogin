package com.example.vitalii.kotlinretrofirxjavalogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitalii.kotlinretrofirxjavalogin.adapter.CustomClickListener
import com.example.vitalii.kotlinretrofirxjavalogin.adapter.ProductAdapter
import com.example.vitalii.kotlinretrofirxjavalogin.model.Product
import com.example.vitalii.kotlinretrofirxjavalogin.viewmodel.ProductViewModel

import com.example.vitalii.kotlinretrofirxjavalogin.fragment.ReviewFragment


class ProductsActivity : AppCompatActivity(), CustomClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        recyclerView = findViewById(R.id.recyclerView)
        val viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        viewModel.loadProducts().observe(this, Observer<List<Product>> {createAdapter(it)})
    }

    private fun createAdapter(product: List<Product>) {
        val adapter = ProductAdapter(product, this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun cardClicked(position: Int) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ReviewFragment.newInstance(position), ReviewFragment::class.java.name)
                .addToBackStack(ReviewFragment::class.java.name)
                .commit()
            }

}