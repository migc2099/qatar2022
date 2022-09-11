package com.migc.qatar2022.presentation.screens.group_details

import com.migc.qatar2022.domain.model.Fixture

data class GroupDetailsState(
    val isLoading: Boolean = false,
    val fixture: List<Fixture> = emptyList(),
    val error: String = ""
)
