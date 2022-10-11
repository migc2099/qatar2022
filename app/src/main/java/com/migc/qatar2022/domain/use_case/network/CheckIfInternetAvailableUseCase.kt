package com.migc.qatar2022.domain.use_case.network

import com.migc.qatar2022.domain.repository.NetworkRepository

class CheckIfInternetAvailableUseCase(
    private val repository: NetworkRepository
) {

    operator fun invoke(): Boolean {
        return repository.isInternetAvailable()
    }

}