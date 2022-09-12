package com.migc.qatar2022.data.local.dao

import androidx.room.*
import com.migc.qatar2022.data.local.entity.GroupEntity
import com.migc.qatar2022.data.local.entity.StandingsEntity

@Dao
interface StandingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStandings(standings: List<StandingsEntity>): List<Long>

    @Update
    fun updateStandings(standings: List<StandingsEntity>)

    @Query("SELECT * FROM standings_table WHERE teamId=:teamId")
    suspend fun getStandingByCountryId(teamId: String): StandingsEntity

    @Query("SELECT * FROM standings_table WHERE groupKey=:group")
    suspend fun getStandingsByGroup(group: String): List<StandingsEntity>

    @Query("SELECT * FROM standings_table WHERE groupKey=:group AND groupPosition=:position LIMIT 1")
    suspend fun getStandingByGroupPosition(group: String, position: Int): StandingsEntity

    @Query(
        "SELECT standings_table.teamId AS teamId, " +
                "teams_table.name AS teamName, " +
                "teams_table.flagLocation AS flagLocation, " +
                "(standings_table.goalsInFavor - standings_table.goalsAgainst) AS goalsDifference, " +
                "standings_table.points AS points, " +
                "groups_table.groupId, " +
                "groups_table.groupName " +
                "FROM standings_table " +
                "LEFT JOIN teams_table ON standings_table.teamId = teams_table.teamId " +
                "JOIN groups_table ON standings_table.groupKey = groups_table.groupId " +
                "ORDER BY standings_table.groupKey ASC, " +
                "standings_table.points DESC, " +
                "(standings_table.goalsInFavor - standings_table.goalsAgainst) DESC, " +
                "standings_table.goalsInFavor DESC"
    )
    fun getStandingsPerGroup(): Map<GroupEntity, List<TeamStanding>>

    data class TeamStanding(
        var teamId: String,
        var teamName: String,
        var flagLocation: Int,
        var goalsDifference: Int,
        var points: Int
    )

}

