package com.migc.qatar2022.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.migc.qatar2022.common.Constants
import com.migc.qatar2022.data.remote.dto.CountryInfoDto
import com.migc.qatar2022.data.remote.dto.toCountryInfo
import com.migc.qatar2022.domain.model.CountryInfo
import com.migc.qatar2022.domain.repository.CountriesInfoRepository
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import javax.inject.Inject


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