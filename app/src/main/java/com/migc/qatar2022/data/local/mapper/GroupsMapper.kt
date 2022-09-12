package com.migc.qatar2022.data.local.mapper

import com.migc.qatar2022.data.local.entity.GroupEntity
import com.migc.qatar2022.domain.model.Group

fun GroupEntity.toGroup(): Group {
    return Group(
        groupId = groupId,
        name = groupName
    )
}