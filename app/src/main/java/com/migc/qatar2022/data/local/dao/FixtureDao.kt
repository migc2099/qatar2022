package com.migc.qatar2022.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.migc.qatar2022.data.local.entity.FixtureEntity

@Dao
interface FixtureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFixture(finals: List<FixtureEntity>) : List<Long>

    @Query("SELECT * FROM fixture_table WHERE groupKey=:group")
    suspend fun getGroupMatches(group: String): List<FixtureEntity>

}