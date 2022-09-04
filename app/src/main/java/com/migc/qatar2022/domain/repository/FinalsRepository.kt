package com.migc.qatar2022.domain.repository

import com.migc.qatar2022.domain.model.KnockOutMatch

interface FinalsRepository {

    fun setFinals(): List<KnockOutMatch>
    suspend fun getFinalByRoundKey(roundKey: Int): KnockOutMatch
    suspend fun resetFinals()
    suspend fun updateFinals(finals: List<KnockOutMatch>)

}