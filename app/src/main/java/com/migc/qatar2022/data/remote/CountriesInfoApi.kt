package com.migc.qatar2022.data.remote

import com.migc.qatar2022.data.remote.dto.CountryInfoDto
import retrofit2.http.GET

interface CountriesInfoApi {

    @GET("/migc2099/0f1539d761b77d97567566ce5fa513c7/raw/70ec282814b25f5546507939f6bcf92f9db8afab/teams_ranking_2022.json")
    suspend fun getCountriesInfo(): List<CountryInfoDto>

}