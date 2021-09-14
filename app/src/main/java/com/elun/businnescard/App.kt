package com.elun.businnescard

import android.app.Application
import com.elun.businnescard.data.AppDatabase
import com.elun.businnescard.data.BusinessCardRepository

class App : Application()  {
    val database by lazy {
        AppDatabase.getDatabase(this)}
    val repository by lazy{BusinessCardRepository(database.businessDao())}

}