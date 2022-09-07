package com.migc.qatar2022.data.remote

import com.migc.qatar2022.data.remote.dto.FixtureDto
import retrofit2.http.GET

interface FixtureApi {

    @GET("/feed/json/fifa-world-cup-2022")
    suspend fun getFixture(): List<FixtureDto>

}