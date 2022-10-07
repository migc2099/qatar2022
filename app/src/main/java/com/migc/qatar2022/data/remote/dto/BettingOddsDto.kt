package com.migc.qatar2022.data.remote.dto

import com.migc.qatar2022.domain.model.BettingOdds

data class BettingOddsDto(
    val wc: Int = 0
)

fun BettingOddsDto.toBettingOdds(): BettingOdds {
    return BettingOdds(wc = wc)
}


