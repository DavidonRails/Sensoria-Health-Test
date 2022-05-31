package com.kabira.boredapp.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.kabira.boredapp.R

class LoadingDialog(context: Context) : Dialog(context) {

    private var mpd: Dialog? = null
    private var view_lyt: View? = null

    @SuppressLint("InflateParams")
    fun showDialog(context: Context, cancelable: Boolean) {
        val lyt_Inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var newView = false
        if (view_lyt != null) {
            Log.d("Reub", "Reloading the loading circle")
        } else {
            Log.d("Reub", "Creating the loading circle")
            newView = true
            view_lyt = lyt_Inflater.inflate(
                R.layout.loading_dialog,
                null
            )
        }
        val img = view_lyt!!.findViewById<ImageView>(R.id.img)
        img.setColorFilter(context.resources.getColor(R.color.colorMain))
        val rotation = AnimationUtils.loadAnimation(context, R.anim.progress_animation)
        rotation.repeatCount = Animation.INFINITE
        img.startAnimation(rotation)
        try {
            mpd = Dialog(context, R.style.Theme_Dialog)
            mpd!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            if (newView) {
                mpd!!.setContentView(view_lyt!!)
            }
            mpd!!.setCancelable(cancelable)
            mpd!!.show()
        } catch (e: NotFoundException) {
            e.printStackTrace()
        }
    }

    fun hideDialog() {
        if (mpd != null) {
            if (mpd!!.isShowing) {
                mpd!!.dismiss()
            }
        }
    }
}