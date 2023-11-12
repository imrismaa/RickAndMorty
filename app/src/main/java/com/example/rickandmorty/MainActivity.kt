package com.example.rickandmorty

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.model.RickAndMorty
import com.example.rickandmorty.model.RickAndMortyData
import com.example.rickandmorty.network.APIClient
import com.example.rickandmorty.recyclerview.CharacterAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var characterList: List<RickAndMorty>

    private val apiService = APIClient.getInstance()
    private val call = apiService.getData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        call.enqueue(object : Callback<RickAndMortyData> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<RickAndMortyData>, response: Response<RickAndMortyData>) {
                if (response.isSuccessful) {
                    val characterData = response.body()
                    characterData?.let {
                        characterList = it.results
                        characterAdapter = CharacterAdapter(characterList)
                        recyclerView.adapter = characterAdapter
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        characterAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<RickAndMortyData>, t: Throwable) {
                // Handle failure
            }
        })

        with (binding) {
            recyclerView = rvCharacter
        }
    }
}