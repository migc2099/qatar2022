package com.migc.qatar2022.data.repository

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.common.Constants.NODE_ODDS
import com.migc.qatar2022.common.Constants.PERMISSION_DENIED_MESSAGE
import com.migc.qatar2022.common.Resource
import com.migc.qatar2022.data.remote.dto.BettingOddsDto
import com.migc.qatar2022.data.remote.dto.CountryInfoDto
import com.migc.qatar2022.data.remote.dto.toCountryInfo
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.repository.CountriesInfoRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class CountriesInfoRepositoryImpl @Inject constructor(
    private val context: Context,
//    private val countriesInfoApi: CountriesInfoApi
) : CountriesInfoRepository {

    //    override suspend fun getData(): Result<List<CountryInfo>> {
//        val countriesInfoDto = countriesInfoApi.getCountriesInfo()
//        return try {
//            Result.success(
//                countriesInfoDto.map {
//                    it.toCountryInfo()
//                }
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.failure(e)
//        }
//    }

    override suspend fun getData(): Result<List<CountryInfo>> {
        val gson = Gson()
        val jsonFileString = inputStreamToString(context.assets.open(Constants.TEAMS_RANKINGS_JSON_FILE))!!
        val listUserType: Type = object : TypeToken<List<CountryInfoDto?>?>() {}.type

        val countriesDto: List<CountryInfoDto> = gson.fromJson(jsonFileString, listUserType)
        val countries = countriesDto.map {
            Log.d("getData()", "mapping $it")
            it.toCountryInfo()
        }

        return Result.success(countries)
    }

    override suspend fun getOdds(teamId: String): Resource<BettingOddsDto> {
        val documentReference: DocumentReference = FirebaseFirestore.getInstance()
            .collection(NODE_ODDS)
            .document(teamId)

        return suspendCancellableCoroutine { continuation ->
            documentReference
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        try {
                            val oddDto = document.toObject(BettingOddsDto::class.java)!!
                            continuation.resume(Resource.Success(data = oddDto))
                        } catch (e: Exception) {
                            Log.e("getOdd", e.message.toString())
                            continuation.resumeWithException(e)
                        }
                    } else {
                        continuation.resume(
                            Resource.Error(message = Constants.GETTING_DATA_ERROR_MESSAGE)
                        )
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("CountriesInfoRepo", "getOdds() message ${e.message}")
                    e.message?.let {
                        Log.d("ssd", it)
                        if (it.contains(PERMISSION_DENIED_MESSAGE)) {
                            continuation.resume(Resource.Error(message = Constants.SIGN_IN_REQUIRED_MESSAGE))
                        } else {
                            continuation.resume(Resource.Error(message = Constants.UNEXPECTED_EXCEPTION_ERROR_MESSAGE))
                        }
                    }
                }
        }
    }

    private fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }
}