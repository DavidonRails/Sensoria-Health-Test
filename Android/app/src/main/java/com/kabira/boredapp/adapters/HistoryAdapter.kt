package com.kabira.boredapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kabira.boredapp.R
import com.kabira.boredapp.models.CardModel
import com.kabira.boredapp.utils.AppConstants

class HistoryAdapter(
    private var arrayList: List<CardModel>? = null,
    private var cardListener: CardListener? = null
) : RecyclerView.Adapter<HistoryAdapter.CustomViewHolder>() {

    interface CardListener {
        fun onEvent()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomViewHolder {
        @SuppressLint("InflateParams") val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_card, null, false)
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = lp
        return CustomViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(customViewHolder: CustomViewHolder, pos: Int) {
        val list: CardModel = arrayList!![pos]
        customViewHolder.tvActivity.text = list.activity

        customViewHolder.tvActivity.setOnClickListener {
            AppConstants().selectedCard = pos
            cardListener?.onEvent()
        }
    }

    override fun getItemCount(): Int {
        return if (arrayList != null) arrayList!!.size else 0
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvActivity: TextView = view.findViewById(R.id.tvActivity)
    }
}