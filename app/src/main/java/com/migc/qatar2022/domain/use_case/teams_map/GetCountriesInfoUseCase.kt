package com.migc.qatar2022.domain.use_case.teams_map

import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.repository.CountriesInfoRepository

class GetCountriesInfoUseCase(
    private val countriesInfoRepository: CountriesInfoRepository
) {

    suspend operator fun invoke(): Result<List<CountryInfo>> {
        return countriesInfoRepository.getData()
    }

}