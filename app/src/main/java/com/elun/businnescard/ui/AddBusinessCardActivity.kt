package com.elun.businnescard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.elun.businnescard.App
import com.elun.businnescard.data.BusinessCard
import com.elun.businnescard.databinding.ActivityAddBusinessCardBinding
import android.util.TypedValue
import android.view.View


import com.skydoves.colorpickerview.listeners.ColorListener


class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private var corSelecionada:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
        val colorId: Int = resources.getIdentifier("teal_700", "color", this.packageName)
        val typedValue =  TypedValue()
        resources.getValue(colorId,typedValue,true)
        corSelecionada= typedValue.data
    }



    private val mainViewModel:MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }
    private fun insertListeners() {
        binding.closeBtn.setOnClickListener {
            this.finish()
        }
        binding.corFundo.setOnClickListener{
            val corHextext= "#${corSelecionada!!.toUInt().toString(16)}"

            binding.colorValue.text=corHextext
            //println("Cor:"+binding.colorValue.text)
            binding.colorPickerLayout.visibility = View.VISIBLE
        }
        binding.selectColorBtn.setOnClickListener{
            corSelecionada = binding.colorPickerView.color
            binding.corFundo.setBackgroundColor(
                corSelecionada!!
            )
            binding.colorPickerLayout.visibility = View.GONE
        }
        binding.confirmButton.setOnClickListener {
            if(binding.colorValue.text.equals("#0")) {
                val corHextext = "#${corSelecionada!!.toUInt().toString(16)}"
                binding.colorValue.text = corHextext
            }
            val businessCard = BusinessCard(
                nome = binding.nome.editText?.text.toString(),
                empresa = binding.nomeEmpresa.editText?.text.toString(),
                telefone = binding.telefone.editText?.text.toString(),
                email = binding.email.editText?.text.toString(),
                fundoPersonalizado = binding.colorValue.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.colorPickerView.setColorListener(ColorListener { color, fromUser ->
                binding.selectColorBtn.setBackgroundColor(color)
                binding.colorValue.text= ("#"+color.toUInt().toString(16))
        })
    }
}