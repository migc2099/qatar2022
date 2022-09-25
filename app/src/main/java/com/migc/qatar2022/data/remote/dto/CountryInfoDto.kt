package com.migc.qatar2022.data.remote.dto

import com.migc.qatar2022.domain.model.CountryInfo

data class CountryInfoDto(
    val team_id: String,
    val team_name: String,
    val latitude: Double,
    val longitude: Double,
    val appearances: Int,
    val championships: List<Int> = emptyList(),
    val runner_ups: List<Int> = emptyList(),
    val ranking: Int
)

fun CountryInfoDto.toCountryInfo(): CountryInfo {
    return CountryInfo(
        teamId = team_id,
        teamName = team_name,
        latitude = latitude,
        longitude = longitude,
        appearances = appearances,
        championships = championships,
        runnerUps = runner_ups,
        ranking = ranking
    )
}