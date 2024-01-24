package com.example.medtimev2

class RecordService {
    var records: MutableList<Record> = mutableListOf<Record>()


    fun addNewRecord(new: Record) {
        records.add(new)
    }

    companion object {
        var default: RecordService = RecordService()
    }
}