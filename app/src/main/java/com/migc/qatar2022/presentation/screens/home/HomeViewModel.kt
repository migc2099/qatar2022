package com.migc.qatar2022.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.migc.qatar2022.R
import com.migc.qatar2022.domain.model.Group
import com.migc.qatar2022.domain.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var groups: List<Group>

    init {
        groups = createGroupsMap()
    }

    private fun createGroupsMap(): List<Group> {
        val listA = listOf(
            Team("QAT", "Qatar", R.drawable.flag_qatar),
            Team("ECU", "Ecuador", R.drawable.flag_ecuador),
            Team("SEN", "Senegal", R.drawable.flag_senegal),
            Team("NET", "Netherlands", R.drawable.flag_netherlands)
        )
        val listB = listOf(
            Team("ENG", "Qatar", R.drawable.flag_england),
            Team("IRA", "Ecuador", R.drawable.flag_iran),
            Team("USA", "Senegal", R.drawable.flag_usa),
            Team("WAL", "Netherlands", R.drawable.flag_wales)
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
        val listH = listOf(
            Team("BRA", "Brazil", R.drawable.flag_brazil),
            Team("SER", "Serbia", R.drawable.flag_serbia),
            Team("SWI", "Switzerland", R.drawable.flag_switzerland),
            Team("CAM", "Cameroon", R.drawable.flag_cameroon)
        )
        val listG = listOf(
            Team("POR", "Portugal", R.drawable.flag_portugal),
            Team("GHA", "Ghana", R.drawable.flag_ghana),
            Team("URU", "Uruguay", R.drawable.flag_uruguay),
            Team("KOR", "Korea Republic", R.drawable.flag_south_korea)
        )
        return listOf(
            Group("Group A", listA),
            Group("Group B", listB),
            Group("Group C", listC),
            Group("Group D", listD),
            Group("Group E", listE),
            Group("Group F", listF),
            Group("Group G", listG),
            Group("Group H", listH),
        )
    }

}