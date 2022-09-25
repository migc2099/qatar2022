package com.migc.qatar2022.data.remote

import com.migc.qatar2022.data.remote.dto.CountryInfoDto
import retrofit2.http.GET

interface CountriesInfoApi {

    @GET("/migc2099/0f1539d761b77d97567566ce5fa513c7/raw/a9c53de350db1c3b2d95cec542ecc87fe8d6aa76/teams_ranking_2022.json")
    suspend fun getCountriesInfo(): List<CountryInfoDto>

}