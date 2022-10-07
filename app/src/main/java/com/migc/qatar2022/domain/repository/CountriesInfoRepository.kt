package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.data.remote.dto.BettingOddsDto
import com.migc.qatar2022.domain.model.CountryInfo

interface CountriesInfoRepository {

    suspend fun getData(): Result<List<CountryInfo>>

    suspend fun getOdds(teamId: String): BettingOddsDto

}