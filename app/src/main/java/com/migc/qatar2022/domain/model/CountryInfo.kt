package com.migc.qatar2022.domain.model

data class CountryInfo(
    val teamId: String = "",
    val teamName: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val appearances: Int = 0,
    val championships: List<Int> = emptyList(),
    val runnerUps: List<Int> = emptyList(),
    val ranking: Int = 0
)
