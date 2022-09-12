package com.migc.qatar2022.common

import android.util.Log
import com.migc.qatar2022.R
import com.migc.qatar2022.data.local.entity.FixtureEntity
import com.migc.qatar2022.data.local.entity.GroupEntity
import com.migc.qatar2022.data.local.entity.StandingsEntity
import com.migc.qatar2022.data.local.entity.TeamEntity
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team

object TeamsData {

//    val listA = listOf(
//        Team("QAT", "Qatar", R.drawable.flag_qatar),
//        Team("ECU", "Ecuador", R.drawable.flag_ecuador),
//        Team("SEN", "Senegal", R.drawable.flag_senegal),
//        Team("NET", "Netherlands", R.drawable.flag_netherlands)
//    )
//    val listB = listOf(
//        Team("ENG", "England", R.drawable.flag_england),
//        Team("IRA", "Iran", R.drawable.flag_iran),
//        Team("USA", "USA", R.drawable.flag_usa),
//        Team("WAL", "Wales", R.drawable.flag_wales)
//    )
//    val listC = listOf(
//        Team("ARG", "Argentina", R.drawable.flag_argentina),
//        Team("SAU", "Saudi Arabia", R.drawable.flag_saudi),
//        Team("MEX", "Mexico", R.drawable.flag_mexico),
//        Team("POL", "Poland", R.drawable.flag_poland)
//    )
//    val listD = listOf(
//        Team("FRA", "France", R.drawable.flag_france),
//        Team("DEN", "Denmark", R.drawable.flag_denmark),
//        Team("TUN", "Tunisia", R.drawable.flag_tunisia),
//        Team("AUS", "Australia", R.drawable.flag_australia)
//    )
//    val listE = listOf(
//        Team("SPA", "Spain", R.drawable.flag_spain),
//        Team("GER", "Germany", R.drawable.flag_germany),
//        Team("JAP", "Japan", R.drawable.flag_japan),
//        Team("COS", "Costa Rica", R.drawable.flag_costa_rica)
//    )
//    val listF = listOf(
//        Team("BEL", "Belgium", R.drawable.flag_belgium),
//        Team("CAN", "Canada", R.drawable.flag_canada),
//        Team("MOR", "Morocco", R.drawable.flag_morocco),
//        Team("CRO", "Croatia", R.drawable.flag_croatia)
//    )
//    val listG = listOf(
//        Team("BRA", "Brazil", R.drawable.flag_brazil),
//        Team("SER", "Serbia", R.drawable.flag_serbia),
//        Team("SWI", "Switzerland", R.drawable.flag_switzerland),
//        Team("CAM", "Cameroon", R.drawable.flag_cameroon)
//    )
//    val listH = listOf(
//        Team("POR", "Portugal", R.drawable.flag_portugal),
//        Team("GHA", "Ghana", R.drawable.flag_ghana),
//        Team("URU", "Uruguay", R.drawable.flag_uruguay),
//        Team("KOR", "Korea Republic", R.drawable.flag_south_korea)
//    )
//    val allGroups = listOf(
//        Group("Group A", listA),
//        Group("Group B", listB),
//        Group("Group C", listC),
//        Group("Group D", listD),
//        Group("Group E", listE),
//        Group("Group F", listF),
//        Group("Group G", listG),
//        Group("Group H", listH),
//    )
    val flagsMap: Map<String, Int> = mapOf(
        "QAT" to R.drawable.flag_qatar,
        "ECU" to R.drawable.flag_ecuador,
        "SEN" to R.drawable.flag_senegal,
        "NET" to R.drawable.flag_netherlands,
        "ENG" to R.drawable.flag_england,
        "IRA" to R.drawable.flag_iran,
        "USA" to R.drawable.flag_usa,
        "WAL" to R.drawable.flag_wales,
        "ARG" to R.drawable.flag_argentina,
        "SAU" to R.drawable.flag_saudi,
        "MEX" to R.drawable.flag_mexico,
        "POL" to R.drawable.flag_poland,
        "FRA" to R.drawable.flag_france,
        "DEN" to R.drawable.flag_denmark,
        "TUN" to R.drawable.flag_tunisia,
        "AUS" to R.drawable.flag_australia,
        "SPA" to R.drawable.flag_spain,
        "GER" to R.drawable.flag_germany,
        "JAP" to R.drawable.flag_japan,
        "COS" to R.drawable.flag_costa_rica,
        "BEL" to R.drawable.flag_belgium,
        "CAN" to R.drawable.flag_canada,
        "MOR" to R.drawable.flag_morocco,
        "CRO" to R.drawable.flag_croatia,
        "BRA" to R.drawable.flag_brazil,
        "SER" to R.drawable.flag_serbia,
        "SWI" to R.drawable.flag_switzerland,
        "CAM" to R.drawable.flag_cameroon,
        "POR" to R.drawable.flag_portugal,
        "GHA" to R.drawable.flag_ghana,
        "URU" to R.drawable.flag_uruguay,
        "KOR" to R.drawable.flag_south_korea
    )

    val groups = listOf(
        GroupEntity("A", "Group A"),
        GroupEntity("B", "Group B"),
        GroupEntity("C", "Group C"),
        GroupEntity("D", "Group D"),
        GroupEntity("E", "Group E"),
        GroupEntity("F", "Group F"),
        GroupEntity("G", "Group G"),
        GroupEntity("H", "Group H")
    )

    val groupsFixture = listOf(
        FixtureEntity(1, 1, "A", 1668999600000, "Al Bayt Stadium", "QAT", "ECU"),
        FixtureEntity(2, 1, "A", 1669086000000, "Al Thumama Stadium", "SEN", "NET"),
        FixtureEntity(3, 1, "B", 1669075200000, "Khalifa International Stadium", "ENG", "IRA"),
        FixtureEntity(4, 1, "B", 1669096800000, "Ahmad Bin Ali Stadium", "USA", "WAL"),
        FixtureEntity(5, 1, "D", 1669183200000, "Al Janoub Stadium", "FRA", "AUS"),
        FixtureEntity(6, 1, "D", 1669161600000, "Education City Stadium", "DEN", "TUN"),
        FixtureEntity(7, 1, "C", 1669172400000, "Stadium 974", "MEX", "POL"),
        FixtureEntity(8, 1, "C", 1669150800000, "Lusail Stadium", "ARG", "SAU"),
        FixtureEntity(9, 1, "F", 1669269600000, "Ahmad Bin Ali Stadium", "BEL", "CAN"),
        FixtureEntity(10, 1, "E", 1669258800000, "Al Thumama Stadium", "SPA", "COS"),
        FixtureEntity(11, 1, "E", 1669248000000, "Khalifa International Stadium", "GER", "JAP"),
        FixtureEntity(12, 1, "F", 1669237200000, "Al Bayt Stadium", "MOR", "CRO"),
        FixtureEntity(13, 1, "G", 1669323600000, "Al Janoub Stadium", "SWI", "CAM"),
        FixtureEntity(14, 1, "H", 1669334400000, "Education City Stadium", "URU", "KOR"),
        FixtureEntity(15, 1, "H", 1669345200000, "Stadium 974", "POR", "GHA"),
        FixtureEntity(16, 1, "G", 1669356000000, "Lusail Stadium", "BRA", "SER"),
        FixtureEntity(17, 2, "B", 1669410000000, "Ahmad Bin Ali Stadium", "WAL", "IRA"),
        FixtureEntity(18, 2, "A", 1669420800000, "Al Thumama Stadium", "QAT", "SEN"),
        FixtureEntity(19, 2, "A", 1669431600000, "Khalifa International Stadium", "NET", "ECU"),
        FixtureEntity(20, 2, "B", 1669442400000, "Al Bayt Stadium", "ENG", "USA"),
        FixtureEntity(21, 2, "D", 1669496400000, "Al Janoub Stadium", "TUN", "AUS"),
        FixtureEntity(22, 2, "C", 1669507200000, "Education City Stadium", "POL", "SAU"),
        FixtureEntity(23, 2, "D", 1669518000000, "Stadium 974", "FRA", "DEN"),
        FixtureEntity(24, 2, "C", 1669528800000, "Lusail Stadium", "ARG", "MEX"),
        FixtureEntity(25, 2, "E", 1669582800000, "Ahmad Bin Ali Stadium", "JAP", "COS"),
        FixtureEntity(26, 2, "F", 1669593600000, "Al Thumama Stadium", "BEL", "MOR"),
        FixtureEntity(27, 2, "F", 1669604400000, "Khalifa International Stadium", "CRO", "CAN"),
        FixtureEntity(28, 2, "E", 1669615200000, "Al Bayt Stadium", "SPA", "GER"),
        FixtureEntity(29, 2, "G", 1669669200000, "Al Janoub Stadium", "CAM", "SER"),
        FixtureEntity(30, 2, "H", 1669680000000, "Education City Stadium", "KOR", "GHA"),
        FixtureEntity(31, 2, "G", 1669690800000, "Stadium 974", "BRA", "SWI"),
        FixtureEntity(32, 2, "H", 1669701600000, "Lusail Stadium", "POR", "URU"),
        FixtureEntity(33, 3, "B", 1669788000000, "Ahmad Bin Ali Stadium", "WAL", "ENG"),
        FixtureEntity(34, 3, "B", 1669788000000, "Al Thumama Stadium", "IRA", "USA"),
        FixtureEntity(35, 3, "A", 1669773600000, "Khalifa International Stadium", "ECU", "SEN"),
        FixtureEntity(36, 3, "A", 1669773600000, "Al Bayt Stadium", "NET", "QAT"),
        FixtureEntity(37, 3, "D", 1669860000000, "Al Janoub Stadium", "AUS", "DEN"),
        FixtureEntity(38, 3, "D", 1669860000000, "Education City Stadium", "TUN", "FRA"),
        FixtureEntity(39, 3, "C", 1669874400000, "Stadium 974", "POL", "ARG"),
        FixtureEntity(40, 3, "C", 1669874400000, "Lusail Stadium", "SAU", "MEX"),
        FixtureEntity(41, 3, "F", 1669946400000, "Ahmad Bin Ali Stadium", "CRO", "BEL"),
        FixtureEntity(42, 3, "F", 1669946400000, "Al Thumama Stadium", "CAN", "MOR"),
        FixtureEntity(43, 3, "E", 1669960800000, "Khalifa International Stadium", "JAP", "SPA"),
        FixtureEntity(44, 3, "E", 1669960800000, "Al Bayt Stadium", "COS", "GER"),
        FixtureEntity(45, 3, "H", 1670032800000, "Al Janoub Stadium", "GHA", "URU"),
        FixtureEntity(46, 3, "H", 1670032800000, "Education City Stadium", "KOR", "POR"),
        FixtureEntity(47, 3, "G", 1670047200000, "Stadium 974", "SER", "SWI"),
        FixtureEntity(48, 3, "G", 1670047200000, "Lusail Stadium", "CAM", "BRA"),
    )

    val standings = listOf(
        StandingsEntity("QAT", "A"),
        StandingsEntity("ECU", "A"),
        StandingsEntity("SEN", "A"),
        StandingsEntity("NET", "A"),
        StandingsEntity("ENG", "B"),
        StandingsEntity("IRA", "B"),
        StandingsEntity("USA", "B"),
        StandingsEntity("WAL", "B"),
        StandingsEntity("ARG", "C"),
        StandingsEntity("SAU", "C"),
        StandingsEntity("MEX", "C"),
        StandingsEntity("POL", "C"),
        StandingsEntity("FRA", "D"),
        StandingsEntity("DEN", "D"),
        StandingsEntity("TUN", "D"),
        StandingsEntity("AUS", "D"),
        StandingsEntity("SPA", "E"),
        StandingsEntity("GER", "E"),
        StandingsEntity("JAP", "E"),
        StandingsEntity("COS", "E"),
        StandingsEntity("BEL", "F"),
        StandingsEntity("CAN", "F"),
        StandingsEntity("MOR", "F"),
        StandingsEntity("CRO", "F"),
        StandingsEntity("BRA", "G"),
        StandingsEntity("SER", "G"),
        StandingsEntity("SWI", "G"),
        StandingsEntity("CAM", "G"),
        StandingsEntity("POR", "H"),
        StandingsEntity("GHA", "H"),
        StandingsEntity("URU", "H"),
        StandingsEntity("KOR", "H")
    )

    val teams = listOf(
        TeamEntity("QAT", "Qatar", "A", R.drawable.flag_qatar),
        TeamEntity("ECU", "Ecuador", "A", R.drawable.flag_ecuador),
        TeamEntity("SEN", "Senegal", "A", R.drawable.flag_senegal),
        TeamEntity("NET", "Netherlands", "A", R.drawable.flag_netherlands),
        TeamEntity("ENG", "England", "B", R.drawable.flag_england),
        TeamEntity("IRA", "Iran", "B", R.drawable.flag_iran),
        TeamEntity("USA", "USA", "B", R.drawable.flag_usa),
        TeamEntity("WAL", "Wales", "B", R.drawable.flag_wales),
        TeamEntity("ARG", "Argentina", "C", R.drawable.flag_argentina),
        TeamEntity("SAU", "Saudi Arabia", "C", R.drawable.flag_saudi),
        TeamEntity("MEX", "Mexico", "C", R.drawable.flag_mexico),
        TeamEntity("POL", "Poland", "C", R.drawable.flag_poland),
        TeamEntity("FRA", "France", "D", R.drawable.flag_france),
        TeamEntity("DEN", "Denmark", "D", R.drawable.flag_denmark),
        TeamEntity("TUN", "Tunisia", "D", R.drawable.flag_tunisia),
        TeamEntity("AUS", "Australia", "D", R.drawable.flag_australia),
        TeamEntity("SPA", "Spain", "E", R.drawable.flag_spain),
        TeamEntity("GER", "Germany", "E", R.drawable.flag_germany),
        TeamEntity("JAP", "Japan", "E", R.drawable.flag_japan),
        TeamEntity("COS", "Costa Rica", "E", R.drawable.flag_costa_rica),
        TeamEntity("BEL", "Belgium", "F", R.drawable.flag_belgium),
        TeamEntity("CAN", "Canada", "F", R.drawable.flag_canada),
        TeamEntity("MOR", "Morocco", "F", R.drawable.flag_morocco),
        TeamEntity("CRO", "Croatia", "F", R.drawable.flag_croatia),
        TeamEntity("BRA", "Brazil", "G", R.drawable.flag_brazil),
        TeamEntity("SER", "Serbia", "G", R.drawable.flag_serbia),
        TeamEntity("SWI", "Switzerland", "G", R.drawable.flag_switzerland),
        TeamEntity("CAM", "Cameroon", "G", R.drawable.flag_cameroon),
        TeamEntity("POR", "Portugal", "H", R.drawable.flag_portugal),
        TeamEntity("GHA", "Ghana", "H", R.drawable.flag_ghana),
        TeamEntity("URU", "Uruguay", "H", R.drawable.flag_uruguay),
        TeamEntity("KOR", "Korea Republic", "H", R.drawable.flag_south_korea)
    )

//    fun getTeamsByGroup(groupId: String): List<Team> {
//        return when (groupId.lowercase()) {
//            "A", "a" -> listA
//            "B", "b" -> listB
//            "C", "c" -> listC
//            "D", "d" -> listD
//            "E", "e" -> listE
//            "F", "f" -> listF
//            "G", "g" -> listG
//            "H", "h" -> listH
//            else -> emptyList()
//        }
//    }
//
//    fun getTeamInGroup(teamId: String, groupId: String): Team {
//        var team: Team
//        Log.d("TeamsData.getTeamInGroup", "teamId: $teamId groupId: $groupId")
//        val teams = getTeamsByGroup(groupId)
//        Log.d("TeamsData", teams.toString())
//        teams.let { existingList ->
//            team = existingList.first {
//                Log.d("log", it.toString())
//                it.teamId.lowercase() == teamId.substring(0..2).lowercase()
//            }
//        }
//        return team
//    }
}