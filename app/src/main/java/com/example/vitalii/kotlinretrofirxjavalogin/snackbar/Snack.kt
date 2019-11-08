package com.example.vitalii.kotlinretrofirxjavalogin.snackbar

import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.vitalii.kotlinretrofirxjavalogin.R
import com.example.vitalii.kotlinretrofirxjavalogin.constants.Const.Companion.ACTION
import com.google.android.material.snackbar.Snackbar

class Snack {
    companion object {
         fun onSnack(view: View, text: String){
            val snackBar = Snackbar.make(view, text,
                Snackbar.LENGTH_LONG).setAction(ACTION, null)
            snackBar.setActionTextColor(Color.BLUE)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.LTGRAY)
            val textView = snackBarView.findViewById(R.id.snackbar_text) as TextView
            textView.setTextColor(Color.BLUE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            } else {
                textView.gravity = Gravity.CENTER_HORIZONTAL
            }
            textView.textSize = 20f
            snackBar.show()
        }
    }
}