package com.arpit.tmdbmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arpit.newsapp3.APIService
import com.arpit.newsapp3.MyAdapter
import com.arpit.newsapp3.moviesService
import com.arpit.tmdbtopmovies.ResponseMovie
import com.arpit.tmdbtopmovies.ResultsItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var moviesadapter : MyAdapter
    private var moviesTopList = mutableListOf<ResultsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext , "Activity Loaded " , Toast.LENGTH_LONG).show()

        moviesadapter = MyAdapter(this, moviesTopList)
        rvMovies.adapter = moviesadapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        getMoviesList()
    }

    private fun getMoviesList(){

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = moviesService.apiService.getMovies()
                if (response.isSuccessful) {
                    val moviesList= response.body()!!
                    withContext(Dispatchers.Main) {

                        if (moviesList!= null) {
                    Toast.makeText(applicationContext , " API Loaded " , Toast.LENGTH_LONG).show()
                    // Log.d("Loading" , news.toString() )
                   moviesTopList.addAll(moviesList.results!!)
                    moviesadapter.notifyDataSetChanged()
                }
                    }
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Cannot Load Data" , Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}


//https://image.tmdb.org/t/p/w500//2CAL2433ZeIihfX1Hb2139CX0pW.jpg