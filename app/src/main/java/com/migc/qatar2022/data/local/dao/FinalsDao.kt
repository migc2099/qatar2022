package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.FinalsEntity

@Dao
interface FinalsDao {

    @Update
    suspend fun resetAllFinals(finals: List<FinalsEntity>)

    @Query("SELECT * FROM finals_table WHERE roundKey=:roundKey")
    suspend fun getMatch(roundKey: Int): FinalsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFinals(finals: List<FinalsEntity>)

}