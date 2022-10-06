package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.FixtureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FixtureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFixture(matches: List<FixtureEntity>) : List<Long>

    @Query("SELECT * FROM fixture_table WHERE groupKey=:group")
    fun getGroupMatches(group: String): Flow<List<FixtureEntity>>

    @Update()
    suspend fun updateFixture(results: List<FixtureEntity>)

    @Update()
    suspend fun updateSingleFixture(match: FixtureEntity)

}