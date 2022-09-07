package com.migc.qatar2022.domain.use_case.group_details

import android.util.Log
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.data.remote.dto.toFixture
import com.migc.qatar2022.domain.model.Fixture
import com.migc.qatar2022.domain.repository.FixtureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class GetMatchesByGroupUseCase(
    private val fixtureRepository: FixtureRepository
) {

    operator fun invoke(group: String): Flow<Resource<List<Fixture>>> = flow {
        try {
            emit(Resource.Loading())
            val fixtureDto = fixtureRepository.getMatchesByGroup(group = group)
            val fixtureResult = fixtureDto
                .filter {
                    it.RoundNumber < 4
                }
                .filter {
                    Log.d("filter", "${it.Group.lowercase()} == ${group.lowercase()}" )
                    it.Group.lowercase(Locale.getDefault()) == group.lowercase()
                }
            Log.d("invoke", "outside of operators")
            val values = fixtureResult.map {
                Log.d("map", it.toString())
                it.toFixture()
            }
            emit(Resource.Success(data = values))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check internet connection"))
        }
    }

}