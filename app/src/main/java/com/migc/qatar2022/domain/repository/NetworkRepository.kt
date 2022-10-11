package com.migc.qatar2022.domain.repository

interface NetworkRepository {

    fun isInternetAvailable(): Boolean

}