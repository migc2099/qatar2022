package com.migc.qatar2022.domain.use_case

import com.migc.qatar2022.domain.use_case.group_details.CalculatePointsUseCase
import com.migc.qatar2022.domain.use_case.group_details.CalculateWeightedResultUseCase
import com.migc.qatar2022.domain.use_case.group_details.GetFixtureByGroupUseCase
import com.migc.qatar2022.domain.use_case.group_details.UpdateFixtureUseCase

data class GroupDetailsUseCases(
//    val getMatchesByGroupUseCase: GetMatchesByGroupUseCase,
    val getFixtureByGroupUseCase: GetFixtureByGroupUseCase,
    val updateFixtureUseCase: UpdateFixtureUseCase,
    val calculatePointsUseCase: CalculatePointsUseCase,
    val calculateWeightedResultUseCase: CalculateWeightedResultUseCase
)
