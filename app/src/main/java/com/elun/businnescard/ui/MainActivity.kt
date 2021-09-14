package com.elun.businnescard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.elun.businnescard.App
import com.elun.businnescard.databinding.ActivityMainBinding
import com.elun.businnescard.util.Image

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mainViewModel:MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy {BusinessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvCards.adapter=adapter
        getAllBusinessCard()
        insertListeners()
    }
    private fun insertListeners(){
        binding.fabAddCartao.setOnClickListener {
            val intent = Intent(this, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {card->
                Image.share(this@MainActivity,card)
        }
    }
    private fun getAllBusinessCard(){

        mainViewModel.getAll().observe(this){businessCards->

            adapter.submitList(businessCards)


        }
    }
}