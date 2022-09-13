package com.migc.qatar2022.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.migc.qatar2022.data.local.dao.*
import com.migc.qatar2022.data.local.entity.*

@Database(
    entities = [
        GroupEntity::class,
        StandingsEntity::class,
        PlayoffEntity::class,
        TeamEntity::class,
        FixtureEntity::class
    ],
    version = 1
)
abstract class QatarDatabase : RoomDatabase() {

    abstract val groupDao: GroupDao
    abstract val standingsDao: StandingsDao
    abstract val playoffDao: PlayoffDao
    abstract val teamDao: TeamDao
    abstract val fixtureDao: FixtureDao

}