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

    @Query(
        "SELECT * FROM standings_table WHERE groupPosition=:position " +
                "ORDER BY points DESC, " +
                "(goalsInFavor - goalsAgainst) DESC, " +
                "goalsInFavor DESC"
    )
    suspend fun getStandingsByGroupPosition(position: Int): List<StandingsEntity>

    @Query("UPDATE OR IGNORE standings_table SET maxStage=:stage WHERE teamId=:teamId")
    suspend fun updateTeamStage(teamId: String, stage: Int)

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

    @Query(
        "SELECT teamId, gamesPlayed, " +
                "CASE " +
                "WHEN gamesPlayed = 3 THEN 1 ELSE 0 " +
                "END AS result " +
                "FROM standings_table WHERE groupKey=:groupKey"
    )
    fun areGroupGamesCompleted(groupKey: String): List<TeamGroupGamesPlayed>

    data class TeamGroupGamesPlayed(
        var teamId: String,
        var gamesPlayed: Int,
        var result: Int
    )

//    @Query("SELECT " +
//            "standings_table.teamId AS teamId, " +
//            "teams_table.teamId AS teamName, " +
//            "teams_table.flagLocation AS flagLocation, " +
//            "standings_table.groupKey AS groupKey, " +
//            "standings_table.groupPosition AS position " +
//            "FROM standings_table " +
//            "LEFT JOIN teams_table ON standings_table.teamId = teams_table.teamId " +
//            "WHERE standings_table.groupKey=:groupKey")
//    fun getQualifiedTeams(groupKey: String): List<QualifiedTeam>
//
//    data class QualifiedTeam(
//        var teamId: String,
//        var teamName: String,
//        var flagLocation: Int,
//        var groupKey: String,
//        var position: Int
//    )

}

