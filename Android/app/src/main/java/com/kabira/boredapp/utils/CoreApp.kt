package com.kabira.boredapp.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kabira.boredapp.models.CardModel
import java.lang.reflect.Type


class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun isNetworkConnection(context: Context): Boolean {
        val connectivityManager =
            (context.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager)
        return (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isAvailable
                && connectivityManager.activeNetworkInfo!!.isConnected)
    }

    fun saveCards(context: Context, cards: ArrayList<CardModel>?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        try {
            val gson = Gson()
            val json = gson.toJson(cards)
            editor.putString("Cards", json)
            editor.apply() // This line is IMPORTANT !!!
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getHistories(context: Context): ArrayList<CardModel>? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = prefs.getString("Cards", null)
        val type: Type = object : TypeToken<ArrayList<CardModel>?>() {}.type
        return gson.fromJson(json, type)
    }
}