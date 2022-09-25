package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.remote.CountriesInfoApi
import com.migc.qatar2022.data.remote.dto.toCountryInfo
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.repository.CountriesInfoRepository
import javax.inject.Inject

class CountriesInfoRepositoryImpl @Inject constructor(
    private val countriesInfoApi: CountriesInfoApi
) : CountriesInfoRepository {

    override suspend fun getData(): Result<List<CountryInfo>> {
        val countriesInfoDto = countriesInfoApi.getCountriesInfo()
        return try {
            Result.success(
                countriesInfoDto.map {
                    it.toCountryInfo()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}