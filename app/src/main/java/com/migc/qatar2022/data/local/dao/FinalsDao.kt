package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.FinalsEntity

@Dao
interface FinalsDao {

    @Update
    suspend fun resetAllFinals(finalsEntity: FinalsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFinals(vararg finalsEntities: FinalsEntity)

}