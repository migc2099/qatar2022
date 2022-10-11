package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.network.CheckIfInternetAvailableUseCase

class NetworkUseCases(
    val checkIfInternetAvailableUseCase: CheckIfInternetAvailableUseCase
)