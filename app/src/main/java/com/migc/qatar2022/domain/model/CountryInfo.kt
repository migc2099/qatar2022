package com.migc.qatar2022.domain.model

data class CountryInfo(
    val teamId: String,
    val teamName: String,
    val latitude: Double,
    val longitude: Double,
    val championships: List<Int>? = emptyList(),
    val finals: List<Int>? = emptyList(),
    val ranking: Int
)
