package com.migc.qatar2022.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.migc.qatar2022.common.Constants.COUNTRIES_TABLE

@Entity(tableName = COUNTRIES_TABLE)
data class CountryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val federation: String
)
