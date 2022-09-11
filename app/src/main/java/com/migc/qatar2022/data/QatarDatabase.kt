package com.migc.qatar2022.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.migc.qatar2022.data.local.dao.*
import com.migc.qatar2022.data.local.entity.*

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