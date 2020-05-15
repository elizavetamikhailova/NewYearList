package com.happy.newyearlist.db.converters

import com.happy.newyearlist.db.RecordEntity
import com.happy.newyearlist.models.Record

fun RecordEntity.toRecord() : Record{
    return Record(
        id = id,
        recordTitle = recordTitle,
        isDone = isDone
    )
}

fun Record.toRecordEntity() : RecordEntity{
    return RecordEntity(
        id = id,
        recordTitle = recordTitle,
        isDone = isDone
    )
}