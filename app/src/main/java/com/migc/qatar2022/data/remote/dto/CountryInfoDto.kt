package com.migc.qatar2022.data.remote.dto

import com.migc.qatar2022.domain.model.CountryInfo

data class CountryInfoDto(
    val championships: List<Int>? = emptyList(),
    val finals: List<Int>? = emptyList(),
    val latitude: Double,
    val longitude: Double,
    val ranking: Int,
    val team_id: String,
    val team_name: String
)

fun CountryInfoDto.toCountryInfo(): CountryInfo {
    return CountryInfo(
        teamId = team_id,
        teamName = team_name,
        latitude = latitude,
        longitude = longitude,
        championships = championships,
        finals = finals,
        ranking = ranking
    )
}