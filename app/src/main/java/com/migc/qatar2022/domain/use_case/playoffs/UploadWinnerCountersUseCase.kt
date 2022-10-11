package com.migc.qatar2022.domain.use_case.playoffs

import android.util.Log
import com.migc.qatar2022.common.Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE
import com.migc.qatar2022.common.Constants.CONNECTION_EXCEPTION_ERROR_MESSAGE
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.model.Team
import com.migc.qatar2022.domain.repository.PlayoffsRepository
import retrofit2.HttpException
import java.io.IOException

class UploadWinnerCountersUseCase(
    private val playoffsRepository: PlayoffsRepository
) {
    suspend operator fun invoke(teams: List<Team>): Resource<Boolean> {
        return try {
            playoffsRepository.uploadWinners(teams)
            Log.d("invoke()", "success")
            Resource.Success(data = true)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: UNEXPECTED_EXCEPTION_ERROR_MESSAGE)
        } catch (e: IOException) {
            Resource.Error(CONNECTION_EXCEPTION_ERROR_MESSAGE)
        }
    }
}