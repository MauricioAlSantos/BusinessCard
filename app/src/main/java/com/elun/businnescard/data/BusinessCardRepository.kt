package com.elun.businnescard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao) {
    fun insert(businessCard: BusinessCard)= runBlocking{
            launch (Dispatchers.IO){

               dao.insert(businessCard)
            }
    }
    fun deleteById(id: Int) = runBlocking{
        launch(Dispatchers.IO){
            dao.deleteById(id)
        }
    }
    fun getAll()=dao.getAll()
}