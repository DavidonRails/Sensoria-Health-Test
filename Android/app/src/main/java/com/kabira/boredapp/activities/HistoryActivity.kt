package com.kabira.boredapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kabira.boredapp.R
import com.kabira.boredapp.adapters.HistoryAdapter
import com.kabira.boredapp.models.CardModel
import com.kabira.boredapp.utils.AppConstants
import com.kabira.boredapp.utils.CoreApp
import java.util.*

class HistoryActivity : AppCompatActivity(), HistoryAdapter.CardListener {

    var cards : List<CardModel>? = null
    private var relCardView : RelativeLayout? = null
    private var tvActivity : TextView? = null
    private var tvType : TextView? = null
    private var tvParticipants : TextView? = null
    private var tvPrice : TextView? = null
    private var tvLink : TextView? = null
    private var tvKey : TextView? = null
    private var tvAccessibility : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        initUI()
    }

    private fun initUI() {
        val tvHistory: RecyclerView = findViewById(R.id.tvHistory)
        relCardView = findViewById(R.id.relCardView)
        val linClose : LinearLayout = findViewById(R.id.linClose)
        tvActivity = findViewById(R.id.tvActivity)
        tvType= findViewById(R.id.tvType)
        tvParticipants= findViewById(R.id.tvParticipants)
        tvPrice = findViewById(R.id.tvPrice)
        tvLink = findViewById(R.id.tvLink)
        tvKey = findViewById(R.id.tvKey)
        tvAccessibility = findViewById(R.id.tvAccessibility)

        relCardView?.visibility = View.GONE

        @SuppressLint("WrongConstant") val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mLayoutManager.isSmoothScrollbarEnabled = true
        mLayoutManager.recycleChildrenOnDetach = true
        tvHistory.layoutManager = mLayoutManager

        cards = CoreApp().getHistories(this@HistoryActivity)?.reversed()
        if (cards != null && cards!!.isNotEmpty()) {
            val historyAdapter = HistoryAdapter(cards, this)
            tvHistory.adapter = historyAdapter
            historyAdapter.notifyDataSetChanged()
        }

        linClose.setOnClickListener {
            relCardView?.visibility = View.GONE
        }
    }

    override fun onEvent() {
        relCardView?.visibility = View.VISIBLE
        tvActivity?.text = cards?.get(AppConstants().selectedCard)?.activity
        tvType?.text = cards?.get(AppConstants().selectedCard)?.type
        tvParticipants?.text = cards?.get(AppConstants().selectedCard)?.participants
        tvPrice?.text = cards?.get(AppConstants().selectedCard)?.price
        tvLink?.text = cards?.get(AppConstants().selectedCard)?.link
        tvKey?.text = cards?.get(AppConstants().selectedCard)?.key
        tvAccessibility?.text = cards?.get(AppConstants().selectedCard)?.accessibility
    }
}