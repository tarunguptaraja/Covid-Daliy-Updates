package com.tarunguptaraja.coviddailyupdates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.tarunguptaraja.khabrilal.MySingleton

class HomeActivity : AppCompatActivity() {


    lateinit var mAdapter:CasesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
        mAdapter = CasesListAdapter()
        recyclerView.adapter=mAdapter
    }



    private fun fetchData() {
//        val list=ArrayList<String>()
//        for(i in 0 until 100)
//            list.add("item $i")
//        return list


        val url = "https://api.rootnet.in/covid19-in/stats/latest"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {

                val casesJsonArray = it.getJSONArray("regional")
                val casesArray = ArrayList<Cases>()
                for (i in 0 until casesJsonArray.length()) {
                    val casesJsonObject = casesJsonArray.getJSONObject(i)
                    val case = Cases(
                        casesJsonObject.getString("loc"),
                        casesJsonObject.getString("totalConfirmed")
                    )
                    casesArray.add(case)
                }
                mAdapter.updateCases(casesArray)

            },
            { error ->

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }



}