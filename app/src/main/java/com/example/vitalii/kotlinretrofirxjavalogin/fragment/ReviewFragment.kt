package com.example.vitalii.kotlinretrofirxjavalogin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitalii.kotlinretrofirxjavalogin.MainActivity
import com.example.vitalii.kotlinretrofirxjavalogin.R
import com.example.vitalii.kotlinretrofirxjavalogin.adapter.ReviewAdapter
import com.example.vitalii.kotlinretrofirxjavalogin.constants.Const.Companion.POSITION
import com.example.vitalii.kotlinretrofirxjavalogin.model.PostReview
import com.example.vitalii.kotlinretrofirxjavalogin.model.Review
import com.example.vitalii.kotlinretrofirxjavalogin.snackbar.Snack.Companion.onSnack
import com.example.vitalii.kotlinretrofirxjavalogin.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {
    private var idProduct: Int = 0
    private var mViewModel: ReviewViewModel? = null
    private var list : ArrayList<Review>? = null
    private var adapter: ReviewAdapter? = null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    companion object {
        fun newInstance(position: Int): ReviewFragment {
            val fragment = ReviewFragment()
            val args = Bundle()
            args.putInt(POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ReviewViewModel::class.java)
        mViewModel!!.loadReviewProduct(idProduct).observe(this, Observer<List<Review>> {createAdapter(it)})

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_review, container, false)
        if (arguments != null){
            idProduct = arguments!!.getInt(POSITION)

        }

        val button = view.findViewById<Button>(R.id.btnSubmitReview)
        button.setOnClickListener {
            val token: String = context!!.let { it1 -> MainActivity.getToken(it1) }
            val rate= rating.rating.toInt()
            val review = MainActivity.validateTextInput(etReview)
            if (token.isNotEmpty() && review.isNotEmpty()) {
                mViewModel!!.postReviewProduct(token, idProduct, rate, review).
                    observe(this, Observer<PostReview>{Log.e("review", it.toString()); etReview.editText!!.text.clear()})
            } else {
                onSnack(view, getString(R.string.please_register))
            }
        }
        return view
    }



    private fun createAdapter(reviews: List<Review>) {
        adapter = ReviewAdapter(reviews)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        rvReview.layoutManager = llm
        rvReview.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }
}
