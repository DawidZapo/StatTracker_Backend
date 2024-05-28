package com.stat_tracker.dto.team;

import com.stat_tracker.dto.team.helper.Record;

import java.util.LinkedList;
import java.util.List;

public class TeamWithRecordsDto {
    List<Record> records = new LinkedList<>();

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
