package com.migc.qatar2022.data.repository

import com.migc.qatar2022.data.QatarDatabase
import com.migc.qatar2022.data.local.mapper.toFinalsEntity
import com.migc.qatar2022.data.local.mapper.toKnockOutMatch
import com.migc.qatar2022.domain.model.KnockOutMatch
import com.migc.qatar2022.domain.repository.FinalsRepository

class FinalsRepositoryImpl constructor(
    private val qatarDatabase: QatarDatabase
) : FinalsRepository {

    override fun setFinals(): List<KnockOutMatch> {
        return listOf(
            KnockOutMatch(49),
            KnockOutMatch(50),
            KnockOutMatch(51),
            KnockOutMatch(52),
            KnockOutMatch(53),
            KnockOutMatch(54),
            KnockOutMatch(55),
            KnockOutMatch(56),
            KnockOutMatch(57),
            KnockOutMatch(58),
            KnockOutMatch(59),
            KnockOutMatch(60),
            KnockOutMatch(61),
            KnockOutMatch(62),
            KnockOutMatch(63),
            KnockOutMatch(64)
        )
    }

    override suspend fun getFinalByRoundKey(roundKey: Int): KnockOutMatch {
        val finalsEntity = qatarDatabase.finalsDao.getMatch(roundKey)
        return finalsEntity.toKnockOutMatch()
    }

    override suspend fun resetFinals() {
        val finalsEntities = setFinals().map {
            it.toFinalsEntity()
        }
        qatarDatabase.finalsDao.updateFinals(finalsEntities)
    }

    override suspend fun updateFinals(finals: List<KnockOutMatch>) {
        val finalsEntities = finals.map {
            it.toFinalsEntity()
        }
        qatarDatabase.finalsDao.updateFinals(finalsEntities)
    }

}