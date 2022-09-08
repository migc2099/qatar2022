package com.migc.qatar2022.common

import android.util.Log
import com.migc.qatar2022.R
import com.migc.qatar2022.data.local.entity.FixtureEntity
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team

object TeamsData {

    val listA = listOf(
        Team("QAT", "Qatar", R.drawable.flag_qatar),
        Team("ECU", "Ecuador", R.drawable.flag_ecuador),
        Team("SEN", "Senegal", R.drawable.flag_senegal),
        Team("NET", "Netherlands", R.drawable.flag_netherlands)
    )
    val listB = listOf(
        Team("ENG", "England", R.drawable.flag_england),
        Team("IRA", "Iran", R.drawable.flag_iran),
        Team("USA", "USA", R.drawable.flag_usa),
        Team("WAL", "Wales", R.drawable.flag_wales)
    )
    val listC = listOf(
        Team("ARG", "Argentina", R.drawable.flag_argentina),
        Team("SAU", "Saudi Arabia", R.drawable.flag_saudi),
        Team("MEX", "Mexico", R.drawable.flag_mexico),
        Team("POL", "Poland", R.drawable.flag_poland)
    )
    val listD = listOf(
        Team("FRA", "France", R.drawable.flag_france),
        Team("DEN", "Denmark", R.drawable.flag_denmark),
        Team("TUN", "Tunisia", R.drawable.flag_tunisia),
        Team("AUS", "Australia", R.drawable.flag_australia)
    )
    val listE = listOf(
        Team("SPA", "Spain", R.drawable.flag_spain),
        Team("GER", "Germany", R.drawable.flag_germany),
        Team("JAP", "Japan", R.drawable.flag_japan),
        Team("COS", "Costa Rica", R.drawable.flag_costa_rica)
    )
    val listF = listOf(
        Team("BEL", "Belgium", R.drawable.flag_belgium),
        Team("CAN", "Canada", R.drawable.flag_canada),
        Team("MOR", "Morocco", R.drawable.flag_morocco),
        Team("CRO", "Croatia", R.drawable.flag_croatia)
    )
    val listG = listOf(
        Team("BRA", "Brazil", R.drawable.flag_brazil),
        Team("SER", "Serbia", R.drawable.flag_serbia),
        Team("SWI", "Switzerland", R.drawable.flag_switzerland),
        Team("CAM", "Cameroon", R.drawable.flag_cameroon)
    )
    val listH = listOf(
        Team("POR", "Portugal", R.drawable.flag_portugal),
        Team("GHA", "Ghana", R.drawable.flag_ghana),
        Team("URU", "Uruguay", R.drawable.flag_uruguay),
        Team("KOR", "Korea Republic", R.drawable.flag_south_korea)
    )
    val allGroups = listOf(
        Group("Group A", listA),
        Group("Group B", listB),
        Group("Group C", listC),
        Group("Group D", listD),
        Group("Group E", listE),
        Group("Group F", listF),
        Group("Group G", listG),
        Group("Group H", listH),
    )
    val groupsFixture = listOf(
        FixtureEntity(1, 1, "A", 0, "Al Bayt Stadium", "QAT", "ECU"),
        FixtureEntity(2, 1, "A", 0, "Al Thumama Stadium", "SEN", "NET"),
        FixtureEntity(3, 1, "B", 0, "Khalifa International Stadium", "ENG", "IRA"),
        FixtureEntity(4, 1, "B", 0, "Ahmad Bin Ali Stadium", "USA", "WAL"),
        FixtureEntity(5, 1, "D", 0, "Al Janoub Stadium", "FRA", "AUS"),
        FixtureEntity(6, 1, "D", 0, "Education City Stadium", "DEN", "TUN"),
        FixtureEntity(7, 1, "C", 0, "Stadium 974", "MEX", "POL"),
        FixtureEntity(8, 1, "C", 0, "Lusail Stadium", "ARG", "SAU"),
        FixtureEntity(9, 1, "F", 0, "Ahmad Bin Ali Stadium", "BEL", "CAN"),
        FixtureEntity(10, 1, "E", 0, "Al Thumama Stadium", "SPA", "COS"),
        FixtureEntity(11, 1, "E", 0, "Khalifa International Stadium", "GER", "JAP"),
        FixtureEntity(12, 1, "F", 0, "Al Bayt Stadium", "MOR", "CRO"),
        FixtureEntity(13, 1, "G", 0, "Al Janoub Stadium", "SWI", "CAM"),
        FixtureEntity(14, 1, "H", 0, "Education City Stadium", "URU", "KOR"),
        FixtureEntity(15, 1, "H", 0, "Stadium 974", "POR", "GHA"),
        FixtureEntity(16, 1, "G", 0, "Lusail Stadium", "BRA", "SER"),
        FixtureEntity(17, 2, "B", 0, "Ahmad Bin Ali Stadium", "WAL", "IRA"),
        FixtureEntity(18, 2, "A", 0, "Al Thumama Stadium", "QAT", "SEN"),
        FixtureEntity(19, 2, "A", 0, "Khalifa International Stadium", "NET", "ECU"),
        FixtureEntity(20, 2, "B", 0, "Al Bayt Stadium", "ENG", "USA"),
        FixtureEntity(21, 2, "D", 0, "Al Janoub Stadium", "TUN", "AUS"),
        FixtureEntity(22, 2, "C", 0, "Education City Stadium", "POL", "SAU"),
        FixtureEntity(23, 2, "D", 0, "Stadium 974", "FRA", "DEN"),
        FixtureEntity(24, 2, "C", 0, "Lusail Stadium", "ARG", "MEX"),
        FixtureEntity(25, 2, "E", 0, "Ahmad Bin Ali Stadium", "JAP", "COS"),
        FixtureEntity(26, 2, "F", 0, "Al Thumama Stadium", "BEL", "MOR"),
        FixtureEntity(27, 2, "F", 0, "Khalifa International Stadium", "CRO", "CAN"),
        FixtureEntity(28, 2, "E", 0, "Al Bayt Stadium", "SPA", "GER"),
        FixtureEntity(29, 2, "G", 0, "Al Janoub Stadium", "CAM", "SER"),
        FixtureEntity(30, 2, "H", 0, "Education City Stadium", "KOR", "GHA"),
        FixtureEntity(31, 2, "G", 0, "Stadium 974", "BRA", "SWI"),
        FixtureEntity(32, 2, "H", 0, "Lusail Stadium", "POR", "URU"),
        FixtureEntity(33, 3, "B", 0, "Ahmad Bin Ali Stadium", "WAL", "ENG"),
        FixtureEntity(34, 3, "B", 0, "Al Thumama Stadium", "IRA", "USA"),
        FixtureEntity(35, 3, "A", 0, "Khalifa International Stadium", "ECU", "SEN"),
        FixtureEntity(36, 3, "A", 0, "Al Bayt Stadium", "NET", "QAT"),
        FixtureEntity(37, 3, "D", 0, "Al Janoub Stadium", "AUS", "DEN"),
        FixtureEntity(38, 3, "D", 0, "Education City Stadium", "TUN", "FRA"),
        FixtureEntity(39, 3, "C", 0, "Stadium 974", "POL", "ARG"),
        FixtureEntity(40, 3, "C", 0, "Lusail Stadium", "SAU", "MEX"),
        FixtureEntity(41, 3, "F", 0, "Ahmad Bin Ali Stadium", "CRO", "BEL"),
        FixtureEntity(42, 3, "F", 0, "Al Thumama Stadium", "CAN", "MOR"),
        FixtureEntity(43, 3, "E", 0, "Khalifa International Stadium", "JAP", "SPA"),
        FixtureEntity(44, 3, "E", 0, "Al Bayt Stadium", "COS", "GER"),
        FixtureEntity(45, 3, "H", 0, "Al Janoub Stadium", "GHA", "URU"),
        FixtureEntity(46, 3, "H", 0, "Education City Stadium", "KOR", "POR"),
        FixtureEntity(47, 3, "G", 0, "Stadium 974", "SER", "SWI"),
        FixtureEntity(48, 3, "G", 0, "Lusail Stadium", "CAM", "BRA"),
    )

    fun getTeamsByGroup(groupId: String): List<Team> {
        return when (groupId.lowercase()) {
            "A", "a" -> listA
            "B", "b" -> listB
            "C", "c" -> listC
            "D", "d" -> listD
            "E", "e" -> listE
            "F", "f" -> listF
            "G", "g" -> listG
            "H", "h" -> listH
            else -> emptyList()
        }
    }

    fun getTeamInGroup(teamId: String, groupId: String): Team {
        var team: Team
        Log.d("TeamsData.getTeamInGroup", "teamId: $teamId groupId: $groupId")
        val teams = getTeamsByGroup(groupId)
        Log.d("TeamsData", teams.toString())
        teams.let { existingList ->
            team = existingList.first {
                Log.d("log", it.toString())
                it.teamId.lowercase() == teamId.substring(0..2).lowercase()
            }
        }
        return team
    }
}