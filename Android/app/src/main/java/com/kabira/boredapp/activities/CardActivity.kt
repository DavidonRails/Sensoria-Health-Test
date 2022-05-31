package com.kabira.boredapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kabira.boredapp.R
import com.kabira.boredapp.apiServices.ApiEndpointInterface
import com.kabira.boredapp.models.CardModel
import com.kabira.boredapp.utils.AppConstants
import com.kabira.boredapp.utils.CoreApp
import com.kabira.boredapp.utils.LoadingDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class CardActivity : AppCompatActivity() {

    private lateinit var loadingDialog : LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        initUI()
    }

    private fun initUI() {
        val btnGenerate: TextView = findViewById(R.id.btnGenerate)
        val tvHistory: TextView = findViewById(R.id.tvHistory)
        val relCardView : RelativeLayout = findViewById(R.id.relCardView)
        val linClose : LinearLayout = findViewById(R.id.linClose)
        val tvActivity: TextView = findViewById(R.id.tvActivity)
        val tvType: TextView = findViewById(R.id.tvType)
        val tvParticipants: TextView = findViewById(R.id.tvParticipants)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val tvLink: TextView = findViewById(R.id.tvLink)
        val tvKey: TextView = findViewById(R.id.tvKey)
        val tvAccessibility: TextView = findViewById(R.id.tvAccessibility)

        relCardView.visibility = View.GONE

        tvHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        linClose.setOnClickListener {
            relCardView.visibility = View.GONE
        }

        btnGenerate.setOnClickListener {
            if (CoreApp().isNetworkConnection(this@CardActivity)) {
                loadingDialog = LoadingDialog(context = this)
                loadingDialog.showDialog(this, false)
                val retrofit = Retrofit.Builder()
                    .baseUrl(AppConstants().BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiService : ApiEndpointInterface = retrofit.create(ApiEndpointInterface::class.java)
                val callCards : Call<CardModel> = apiService.getCards()
                callCards.enqueue(object : Callback<CardModel> {
                    override fun onResponse(call: Call<CardModel>, response: Response<CardModel>) {
                        loadingDialog.hideDialog()
                        val card: CardModel? = response.body()

                        relCardView.visibility = View.VISIBLE
                        tvActivity.text = card?.activity
                        tvType.text = card?.type
                        tvParticipants.text = card?.participants
                        tvPrice.text = card?.price
                        tvLink.text = card?.link
                        tvKey.text = card?.key
                        tvAccessibility.text = card?.accessibility

                        var cards = CoreApp().getHistories(this@CardActivity)
                        if (cards != null) {
                            var isExisted = false
                            for(savedCard : CardModel in cards) {
                                if (savedCard.key == card?.key) {
                                    isExisted = true
                                }
                            }

                            if (!isExisted) {
                                card?.let { it1 -> cards!!.add(it1) }
                                CoreApp().saveCards(this@CardActivity, cards)
                            }
                        } else {
                            cards = ArrayList()
                            card?.let { it1 -> cards.add(it1) }
                            CoreApp().saveCards(this@CardActivity, cards)
                        }
                    }

                    override fun onFailure(call: Call<CardModel>, t: Throwable) {
                        loadingDialog.hideDialog()
                        Toast.makeText(this@CardActivity, "Failed. Please try again later.", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "Network is not available. Please try again later.", Toast.LENGTH_LONG).show()
            }

        }
    }
}