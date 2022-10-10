package com.migc.qatar2022.domain.use_case.playoffs

import android.util.Log
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.repository.PlayoffsRepository

class UploadWinnerCountersUseCase(
    private val playoffsRepository: PlayoffsRepository
) {
    suspend operator fun invoke(teams: List<Team>): Resource<Boolean> {
        return try {
            playoffsRepository.uploadWinners(teams)
            Log.d("invoke()", "success")
            Resource.Success(data = true)
        } catch (e: Exception) {
            Log.e("invoke()", e.message.toString())
            Resource.Error(e.message.toString())
        }
    }
}