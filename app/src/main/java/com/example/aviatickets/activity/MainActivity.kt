package com.example.aviatickets.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ActivityMainBinding
import com.example.aviatickets.fragment.OfferListFragment
import com.example.aviatickets.model.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.MyApi
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllOffers()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, OfferListFragment.newInstance())
            .commit()



    }
    private val baseURL = "https://my-json-server.typicode.com/"
    private val TAG: String = "CHECK_RESPONCE"

    private fun getAllOffers(){
        val off = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        val client = MyApi.instance


        off.getOffers().enqueue(object: Callback<List<Offer>>{
            override fun onResponse(call: Call<List<Offer>>, response: Response<List<Offer>>) {
                println("Httpresponce: ${response.body}")


            }

            override fun onFailure(call: Call<List<Offer>>, t: Throwable) {
            println("Httpresponce: ${t.message}")
            }
        }

        )
    }
}