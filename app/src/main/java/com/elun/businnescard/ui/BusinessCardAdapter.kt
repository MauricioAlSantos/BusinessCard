package com.elun.businnescard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elun.businnescard.data.BusinessCard
import com.elun.businnescard.databinding.ItemBusinnesCardBinding


class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {
    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =   ItemBusinnesCardBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(private val binding: ItemBusinnesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.nome.text = item.nome
            binding.telefone.text = item.telefone
            binding.email.text = item.email
            binding.empresa.text = item.empresa
            binding.itemContent.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.itemContent.setOnClickListener{listenerShare(it)}
            binding.itemContent.setOnClickListener {
                    listenerShare(it)
            }
        }

    }



}

class DiffCallback :DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard)=oldItem==newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard)=oldItem.id==newItem.id
}