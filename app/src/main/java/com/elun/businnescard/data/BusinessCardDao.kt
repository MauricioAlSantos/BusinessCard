package com.elun.businnescard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {
    @Query("SELECT * FROM BusinessCard")
    fun getAll():LiveData<List<BusinessCard>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCard: BusinessCard)
    @Query("DELETE FROM BusinessCard WHERE id= :id")
    fun deleteById(id:Int)
}