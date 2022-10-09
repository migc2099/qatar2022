package com.migc.qatar2022.domain.use_case.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SignInAnonymouslyUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(): Flow<Resource<FirebaseUser?>> = flow {
        try {
            emit(Resource.Loading())
            val user = repository.signInAnonymously()
            Log.d("SignUseCase", "user: ${user!!.uid}")
            emit(Resource.Success(data = user))
        } catch (e: HttpException) {
            emit(Resource.Error("Couldn't reach server. Check internet connection"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}