package com.migc.qatar2022.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.migc.qatar2022.data.local.dao.CountryDao
import com.migc.qatar2022.data.local.dao.FinalsDao
import com.migc.qatar2022.data.local.dao.FixtureDao
import com.migc.qatar2022.data.local.dao.StandingsDao
import com.migc.qatar2022.data.local.entity.CountryEntity
import com.migc.qatar2022.data.local.entity.FinalsEntity
import com.migc.qatar2022.data.local.entity.FixtureEntity
import com.migc.qatar2022.data.local.entity.StandingsEntity

@Database(
    entities = [
        StandingsEntity::class,
        FinalsEntity::class,
        CountryEntity::class,
        FixtureEntity::class
    ],
    version = 1
)
abstract class QatarDatabase : RoomDatabase() {

    abstract val standingsDao: StandingsDao
    abstract val finalsDao: FinalsDao
    abstract val countryDao: CountryDao
    abstract val fixtureDao: FixtureDao

}